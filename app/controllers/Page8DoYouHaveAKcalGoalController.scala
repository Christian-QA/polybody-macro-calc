package controllers

import akka.Done
import com.google.inject.Inject
import forms.DoYouHaveAKcalGoalForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._

import java.util.concurrent.CompletionStage
import scala.concurrent.Future

class Page8DoYouHaveAKcalGoalController @Inject() (
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def doYouHaveAKcalGoalPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.Page8KcalGoal(DoYouHaveAKcalGoalForm.form()))
    }

  def doYouHaveAKcalGoalOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      DoYouHaveAKcalGoalForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.LandingPageController.index())),
          value => {
            val result: Future[Done] =
              cache.set("kcalGoal", value.kcals)

            Future.successful(
              Redirect(
                routes.Page9DoYouHaveAProteinGoalController
                  .doYouHaveAProteinGoalPageLoad()
              )
            )
          }
        )
    }
}