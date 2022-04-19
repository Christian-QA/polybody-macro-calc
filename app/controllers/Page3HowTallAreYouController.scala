package controllers

import akka.Done
import com.google.inject.Inject
import forms.HowTallAreYouForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import views.html.Page3HeightView

import java.util.concurrent.CompletionStage
import scala.concurrent.Future

class Page3HowTallAreYouController @Inject() (
    page3HeightView: Page3HeightView,
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def howTallAreYouPageLoad(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      Future.successful(Ok(page3HeightView(HowTallAreYouForm.form())))
    }

  def howTallAreYouOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      HowTallAreYouForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.LandingPageController.index())),
          value => {
            val result: Future[Done] = cache.set("height", value.height)

            Future.successful(
              Redirect(
                routes.Page4HowMuchDoYouWeighController
                  .howMuchDoYouWeighPageLoad()
              )
            )
          }
        )
    }
}
