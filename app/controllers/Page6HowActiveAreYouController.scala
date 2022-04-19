package controllers

import akka.Done
import com.google.inject.Inject
import forms.HowActiveAreYouForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import views.html.Page6ActivityLevelView

import scala.concurrent.Future

class Page6HowActiveAreYouController @Inject() (
    page6ActivityLevelView: Page6ActivityLevelView,
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def howActiveAreYouPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(page6ActivityLevelView(HowActiveAreYouForm.form()))
    }

  def howActiveAreYouOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      HowActiveAreYouForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.LandingPageController.index())),
          value => {
            val result: Future[Done] =
              cache.set("activityLevel", value.activity)

            Future.successful(
              Redirect(
                routes.Page7ShortSummaryController
                  .shortSummaryPageLoad()
              )
            )
          }
        )
    }
}
