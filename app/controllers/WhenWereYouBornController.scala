package controllers

import akka.Done
import com.google.inject.Inject
import forms.WhenWereYouBornForm
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import play.cache.DefaultAsyncCacheApi

import java.util.concurrent.CompletionStage
import scala.concurrent.Future

class WhenWereYouBornController @Inject() (
    cache: DefaultAsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def whenWereYouBornPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.Age(WhenWereYouBornForm.form()))
    }

  def whenWereYouBornOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      WhenWereYouBornForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.HomeController.index())),
          value => {
            val result: CompletionStage[Done] = cache.set("age", value)

            Future.successful(
              Redirect(routes.HowTallAreYouController.howTallAreYouPageLoad())
            )
          }
        )
    }
}
