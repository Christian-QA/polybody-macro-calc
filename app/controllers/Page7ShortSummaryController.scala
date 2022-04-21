package controllers

import com.google.inject.Inject
import controllers.handler.ErrorHandler
import errors.CustomTimeoutResponse
import forms.ShortSummaryForm
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import services.CacheService
import views.html.{LandingPageView, Page7ShortSummaryView}

import scala.concurrent.Future

class Page7ShortSummaryController @Inject() (
    cacheService: CacheService,
    errorHandler: ErrorHandler,
    page7ShortSummaryView: Page7ShortSummaryView,
    landingPageView: LandingPageView, //TODO - Change to error
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def shortSummaryPageLoad(): Action[AnyContent] = {
    Action.async { implicit request: Request[AnyContent] =>
      println(cacheService.cacheToShortDto)
      cacheService.cacheToShortDto match {
        case Some(value) =>
          println("1" * 100)
          println(value)
          Future.successful(
            Ok(page7ShortSummaryView(ShortSummaryForm.form(), value))
          )
        case None =>
          println("2" * 100)
          errorHandler.handle(CustomTimeoutResponse, this.getClass.getName)
        //BadRequest(views.html.errorViews.ErrorBadRequestView)
      }
    }
  }

  def shortSummaryOnSubmit(): Action[AnyContent] = {
    Action.async { implicit request: Request[AnyContent] =>
      ShortSummaryForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.LandingPageController.index())),
          value =>
            if (value.continue) {
              Future.successful(
                Redirect(
                  routes.Page8DoYouHaveAKcalGoalController
                    .doYouHaveAKcalGoalPageLoad()
                )
              )
            } else {
              Future.successful(
                Redirect(
                  routes.LandingPageController.index()
                )
              )
            }
        )
    }
  }
}
