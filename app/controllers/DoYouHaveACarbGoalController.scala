package controllers

import akka.Done
import com.google.inject.Inject
import forms.DoYouHaveACarbGoalForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._

import scala.concurrent.Future

class DoYouHaveACarbGoalController @Inject() (
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def doYouHaveACarbGoalPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.CarbGoal(DoYouHaveACarbGoalForm.form()))
    }

  def doYouHaveACarbGoalOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      DoYouHaveACarbGoalForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.HomeController.index())),
          value => {
            println(value.carb)
            val result: Future[Done] =
              cache.set("carbGoal", value.carb)

            Future.successful(
              Redirect(
                routes.DoYouWantToUseYourBodyFatController
                  .doYouWantToUseYourBodyFatPageLoad()
              )
            )
          }
        )
    }
}
