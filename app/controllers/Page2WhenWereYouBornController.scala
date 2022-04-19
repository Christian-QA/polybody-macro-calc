package controllers

import akka.Done
import com.google.inject.Inject
import forms.WhenWereYouBornForm
import helpers.MaleOrFemale
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import views.html.Page2AgeView

import java.util.Optional
import java.util.concurrent.CompletionStage
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.{Duration, SECONDS}

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
            Future.successful(Redirect(routes.LandingPageController.index())),
          value => {
            val result: Future[Done] = cache.set("age", value.age)

            val futureMaybeUser: Future[Option[MaleOrFemale]] =
              cache
                .get[MaleOrFemale]("sex")

            println(Await.result(futureMaybeUser, Duration(5, SECONDS)))

            Future.successful(
              Redirect(
                routes.Page3HowTallAreYouController.howTallAreYouPageLoad()
              )
            )
          }
        )
    }
}
