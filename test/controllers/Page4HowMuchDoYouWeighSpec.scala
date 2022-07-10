package controllers

import akka.Done
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{reset, times, verify, when}
import org.scalatest.BeforeAndAfterEach
import play.api.cache.AsyncCacheApi
import play.api.http.Status.{BAD_REQUEST, OK, SEE_OTHER}
import play.api.i18n.{Lang, Langs}
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers.{
  contentAsString,
  defaultAwaitTimeout,
  redirectLocation,
  status
}
import utils.BaseSpec
import views.html.{Page3HeightView, Page4CurrentWeightView}

import scala.concurrent.Future

class Page4HowMuchDoYouWeighSpec extends BaseSpec with BeforeAndAfterEach {

  val page4CurrentWeightView: Page4CurrentWeightView =
    inject[Page4CurrentWeightView]

  val cache: AsyncCacheApi = mock[AsyncCacheApi]

  def controller =
    new Page4HowMuchDoYouWeighController(
      page4CurrentWeightView,
      cache,
      cc,
      messages,
      inject[Langs]
    )

  when(cache.set(any(), any(), any())).thenReturn {
    Future.successful(Done)
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    reset(cache)
  }

  //TODO - Remember to add test when implementing auth

  "Page4HowMuchDoYouWeighController" when {
    "howMuchDoYouWeighPageLoad method" must {
      "open Page4CurrentWeightView" in {
        val request: Future[Result] =
          controller.howMuchDoYouWeighPageLoad()(FakeRequest())

        status(request) mustBe OK
        contentAsString(request) must include(
          messages.apply("page4.current-weight.title")(Lang.defaultLang)
        )
      }
    }

    // TODO - Add weight validation

    "howMuchDoYouWeighOnSubmit method" must {
      "redirect to Page5TargetWeightView with the value set in the cache when a height is inputted" in {
        val result: Future[Result] =
          controller.howMuchDoYouWeighOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("howMuchDoYouWeigh", 200.00.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/what-is-your-target-weight"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "not continue to next page with a bad request status if nothing is selected on submit" in {
        val result: Future[Result] =
          controller.howMuchDoYouWeighOnSubmit()(
            FakeRequest()
          )

        status(result) mustBe BAD_REQUEST
      }
    }
  }
}
