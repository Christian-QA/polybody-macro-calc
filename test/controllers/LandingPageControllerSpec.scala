package controllers

import play.api.http.Status.OK
import play.api.i18n.{Lang, Langs}
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers.{
  LOCATION,
  contentAsString,
  defaultAwaitTimeout,
  redirectLocation,
  status
}
import utils.BaseSpec
import views.html.LandingPageView

import scala.concurrent.Future

class LandingPageControllerSpec extends BaseSpec {

  val landingView: LandingPageView = inject[LandingPageView]

  def controller =
    new LandingPageController(
      landingView,
      cc,
      messages,
      inject[Langs]
    )

  "index method" must {
    "open the landing page" in {
      val request: Future[Result] = controller.index()(FakeRequest())

      status(request) mustBe OK
      contentAsString(request) must include(
        messages.apply("landing.title")(Lang.defaultLang)
      )
    }
  }
}
