package controllers

import com.google.inject.Inject
import controllers.handler.ErrorHandler
import errors.CustomTimeoutResponse
import forms.PreviousWeightForm
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import services.{CacheService, PreviousWeightService}

import scala.concurrent.{ExecutionContext, Future}

class Page14WeightSubmitController @Inject() (
    previousWeightService: PreviousWeightService,
    cacheService: CacheService,
    errorHandler: ErrorHandler,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
)(implicit ec: ExecutionContext)
    extends AbstractController(cc)
    with I18nSupport {

  def wouldYouLikeToSubmitThisWeightPageLoad(): Action[AnyContent] = {
    Action { implicit request: Request[AnyContent] =>
      cacheService.cacheToWeightDouble match {
        case Some(value) =>
          Ok(
            views.html.Page14WeightSubmitView(PreviousWeightForm.form(), value)
          )
        case None => Redirect(routes.LandingPageController.index())
      }
    }
  }

  def wouldYouLikeToSubmitThisWeightOnSubmit(): Action[AnyContent] = {
    Action.async { implicit request: Request[AnyContent] =>
      PreviousWeightForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.LandingPageController.index())),
          value => {
            if (value.save) {
              cacheService.cacheToWeightDouble match {
                case Some(value) =>
                  previousWeightService
                    .addPreviousWeight("Calvin", value)
                    .flatMap {
                      case Left(error) =>
                        errorHandler.handle(error)
                      case Right(value) =>
                        Future.successful(
                          Redirect(
                            routes.LandingPageController
                              .index()
                          )
                        )
                    }
                case None =>
                  errorHandler.handle(CustomTimeoutResponse)
              }
            } else {
              Future.successful(
                Redirect(
                  routes.LandingPageController.index() // Todo - Closing page
                )
              )
            }
          }
        )
    }
  }
}
