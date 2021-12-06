package controllers

import akka.Done
import com.google.inject.Inject
import forms.HowActiveAreYouForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._

import java.util.concurrent.CompletionStage
import scala.concurrent.Future

class HowActiveAreYouController @Inject() (
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def howActiveAreYouPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.ActivityLevel(HowActiveAreYouForm.form()))
    }

  def howActiveAreYouOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      HowActiveAreYouForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.HomeController.index())),
          value => {
            val result: Future[Done] =
              cache.set("activityLevel", value.activity)

            Future.successful(
              Redirect(
                routes.ShortSummaryController
                  .shortSummaryPageLoad()
              )
            )
          }
        )
    }
}
