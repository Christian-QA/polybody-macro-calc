package controllers

import com.google.inject.Inject
import controllers.handler.ErrorHandler
import errors.CustomTimeoutResponse
import forms.ShortSummaryForm
import play.api.data.Form
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import services.CacheService
import utils.TDEECalculator
import views.html.Page7ShortSummaryView

import scala.concurrent.Future

class Page7ShortSummaryController @Inject() (
    cacheService: CacheService,
    errorHandler: ErrorHandler,
    page7ShortSummaryView: Page7ShortSummaryView,
    tdeeCalculator: TDEECalculator,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def shortSummaryPageLoad(): Action[AnyContent] = {
    Action.async { implicit request: Request[AnyContent] =>
      cacheHandler(ShortSummaryForm.form())
    }
  }

  def shortSummaryOnSubmit(): Action[AnyContent] = {
    Action.async { implicit request: Request[AnyContent] =>
      ShortSummaryForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors => {
            cacheHandler(formWithErrors, onSubmit = true)
          },
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
      onSubmit: Boolean = false
  )(implicit request: Request[AnyContent]): Future[Result] = {
    cacheService.cacheToShortDto match {
      case Some(value) =>
        val bmr: Int = tdeeCalculator.bmrMifflinStJeorCalculator(
          value.currentWeight,
          value.height,
          value.dob,
          value.sex
        )
        val tdee: Int = tdeeCalculator.activityFactor(bmr, value.activityLevel)
        if (onSubmit) {
          Future.successful(
            BadRequest(page7ShortSummaryView(form, value, bmr, tdee))
          )
        } else {
          Future.successful(Ok(page7ShortSummaryView(form, value, bmr, tdee)))
        }
      case None =>
        errorHandler.handle(CustomTimeoutResponse, this.getClass.getName)
    }
  }
}
