package controllers

import akka.Done
import com.google.inject.Inject
import forms.DoYouWantToUseYourBodyFatForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import views.html.{Page12BodyFat, Page12BodyFatView}

import scala.concurrent.duration.{Duration, SECONDS}
import scala.concurrent.{Await, Future}

class Page12DoYouWantToUseYourBodyFatController @Inject() (
    page12BodyFatView: Page12BodyFatView,
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def doYouWantToUseYourBodyFatPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(page12BodyFatView(DoYouWantToUseYourBodyFatForm.form()))
    }

  def doYouWantToUseYourBodyFatOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      DoYouWantToUseYourBodyFatForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.LandingPageController.index())),
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
