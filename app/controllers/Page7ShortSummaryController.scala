package controllers

import com.google.inject.Inject
import controllers.handler.ErrorHandler
import errors.CustomTimeoutResponse
import forms.ShortSummaryForm
import play.api.data.Form
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import services.CacheService
import views.html.Page7ShortSummaryView

import scala.concurrent.Future

class Page7ShortSummaryController @Inject() (
    cacheService: CacheService,
    errorHandler: ErrorHandler,
    page7ShortSummaryView: Page7ShortSummaryView,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def shortSummaryPageLoad(): Action[AnyContent] = {
    Action.async { implicit request: Request[AnyContent] =>
      println(cacheService.cacheToShortDto)
      cacheHandler(ShortSummaryForm.form())
    }
  }

  def shortSummaryOnSubmit(): Action[AnyContent] = {
    Action.async { implicit request: Request[AnyContent] =>
      ShortSummaryForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors => cacheHandler(formWithErrors, submit = true),
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
                  routes.Page14WeightSubmitController
                    .wouldYouLikeToSubmitThisWeightPageLoad()
                )
              )
            }
        )
    }
  }

  private def cacheHandler(
      form: Form[ShortSummaryForm],
      submit: Boolean = false
  )(implicit request: Request[AnyContent]): Future[Result] = {
    cacheService.cacheToShortDto match {
      case Some(value) =>
        if (submit) {
          println("1" * 100)
          println(value)
          Future.successful(
            BadRequest(page7ShortSummaryView(form, value))
          )
        } else {
          println("2" * 100)
          println(value)
          Future.successful(
            Ok(page7ShortSummaryView(form, value))
          )
        }
      case None =>
        println("3" * 100)
        errorHandler.handle(CustomTimeoutResponse, this.getClass.getName)
    }
  }
}
