package controllers

import akka.Done
import com.google.inject.Inject
import forms.DoYouHaveAProteinGoalForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import views.html.Page9ProteinGoalView

import scala.concurrent.Future

class Page9DoYouHaveAProteinGoalController @Inject() (
    page9ProteinGoalView: Page9ProteinGoalView,
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def doYouHaveAProteinGoalPageLoad(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      Future.successful(
        Ok(page9ProteinGoalView(DoYouHaveAProteinGoalForm.form()))
      )
    }

  def doYouHaveAProteinGoalOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      DoYouHaveAProteinGoalForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(BadRequest(page9ProteinGoalView(formWithErrors))),
          value => {
            val result: Future[Done] =
              cache.set("proteinGoal", value.protein)

            Future.successful(
              Redirect(
                routes.Page10DoYouHaveAFatGoalController
                  .doYouHaveAFatGoalPageLoad()
              )
            )
          }
        )
    }
}
