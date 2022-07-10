package controllers

import akka.Done
import com.google.inject.Inject
import forms.WhenWereYouBornForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import views.html.Page2AgeView

import scala.concurrent.Future

class Page2WhenWereYouBornController @Inject() (
    page2AgeView: Page2AgeView,
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def whenWereYouBornPageLoad(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      Future.successful(Ok(page2AgeView(WhenWereYouBornForm.form())))
    }

  def whenWereYouBornOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      WhenWereYouBornForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(BadRequest(page2AgeView(formWithErrors))),
          value => {
            val result: Future[Done] = cache.set("age", value.age)

            Future.successful(
              Redirect(
                routes.Page3HowTallAreYouController.howTallAreYouPageLoad()
              )
            )
          }
        )
    }
}
