package controllers

import akka.Done
import com.google.inject.Inject
import forms.{DoYouHaveAProteinGoalForm, HowActiveAreYouForm}
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import play.cache.DefaultAsyncCacheApi

import java.util.concurrent.CompletionStage
import scala.concurrent.Future

class DoYouHaveAProteinGoalController @Inject() (
    cache: DefaultAsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def doYouHaveAProteinGoalPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.ProteinGoal(DoYouHaveAProteinGoalForm.form()))
    }

  def doYouHaveAProteinGoalOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      DoYouHaveAProteinGoalForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.HomeController.index())),
          value => {
            val result: CompletionStage[Done] =
              cache.set("proteinGoal", value)

            Future.successful(
              Redirect(
                routes.DoYouHaveAFatGoalController
                  .doYouHaveAFatGoalPageLoad()
              )
            )
          }
        )
    }
}
