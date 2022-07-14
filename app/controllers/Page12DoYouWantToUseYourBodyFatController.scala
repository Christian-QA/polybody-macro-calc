package controllers

import akka.Done
import com.google.inject.Inject
import forms.DoYouWantToUseYourBodyFatForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import views.html.Page12BodyFatView

import scala.concurrent.Future

class Page12DoYouWantToUseYourBodyFatController @Inject() (
    page12BodyFatView: Page12BodyFatView,
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def doYouWantToUseYourBodyFatPageLoad(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      Future.successful(
        Ok(page12BodyFatView(DoYouWantToUseYourBodyFatForm.form()))
      )
    }

  def doYouWantToUseYourBodyFatOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      DoYouWantToUseYourBodyFatForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(BadRequest(page12BodyFatView(formWithErrors))),
          value => {
            val result: Future[Done] =
              cache.set("bodyFat", value.bodyFat)

            Future.successful(
              Redirect(
                routes.Page13FullSummaryController
                  .fullSummaryPageLoad()
              )
            )
          }
        )
    }
}
