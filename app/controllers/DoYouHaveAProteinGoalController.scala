package controllers

import akka.Done
import com.google.inject.Inject
import forms.DoYouHaveAProteinGoalForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._

import java.util.concurrent.CompletionStage
import scala.concurrent.Future

class DoYouHaveAProteinGoalController @Inject() (
    cache: AsyncCacheApi,
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
            val result: Future[Done] =
              cache.set("proteinGoal", value.protein)

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
