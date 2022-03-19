package controllers

import akka.Done
import com.google.inject.Inject
import forms.DoYouHaveAFatGoalForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._

import scala.concurrent.Future

class Page10DoYouHaveAFatGoalController @Inject() (
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def doYouHaveAFatGoalPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.Page10FatGoal(DoYouHaveAFatGoalForm.form()))
    }

  def doYouHaveAFatGoalOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      DoYouHaveAFatGoalForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.LandingPageController.index())),
          value => {
            val result: Future[Done] =
              cache.set("fatGoal", value.fat)

            Future.successful(
              Redirect(
                routes.Page11DoYouHaveACarbGoalController
                  .doYouHaveACarbGoalPageLoad()
              )
            )
          }
        )
    }
}