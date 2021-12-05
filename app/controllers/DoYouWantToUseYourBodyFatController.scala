package controllers

import akka.Done
import com.google.inject.Inject
import forms.DoYouWantToUseYourBodyFatForm
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import play.cache.DefaultAsyncCacheApi

import java.util.concurrent.CompletionStage
import scala.concurrent.Future

class DoYouWantToUseYourBodyFatController @Inject() (
    cache: DefaultAsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def doYouWantToUseYourBodyFatPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.BodyFat(DoYouWantToUseYourBodyFatForm.form()))
    }

  def doYouWantToUseYourBodyFatOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      DoYouWantToUseYourBodyFatForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.HomeController.index())),
          value => {
            val result: CompletionStage[Done] =
              cache.set("bodyFat", value)

            Future.successful(
              Redirect(
                routes.FullSummaryController
                  .fullSummaryPageLoad()
              )
            )
          }
        )
    }
}
