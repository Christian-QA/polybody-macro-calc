package controllers

import akka.Done
import com.google.inject.Inject
import forms.DoYouHaveACarbGoalForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import views.html.Page11CarbGoalView

import scala.concurrent.Future

class Page11DoYouHaveACarbGoalController @Inject() (
    page11CarbGoalView: Page11CarbGoalView,
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def doYouHaveACarbGoalPageLoad(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      Future.successful(Ok(page11CarbGoalView(DoYouHaveACarbGoalForm.form())))
    }

  def doYouHaveACarbGoalOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      DoYouHaveACarbGoalForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(BadRequest(page11CarbGoalView(formWithErrors))),
          value => {
            val result: Future[Done] =
              cache.set("carbGoal", value.carb)

            Future.successful(
              Redirect(
                routes.Page12DoYouWantToUseYourBodyFatController
                  .doYouWantToUseYourBodyFatPageLoad()
              )
            )
          }
        )
    }
}
