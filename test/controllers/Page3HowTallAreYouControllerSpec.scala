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
import views.html.{Page2AgeView, Page3HeightView}

import java.time.LocalDate
import scala.concurrent.Future

class Page3HowTallAreYouControllerSpec
    extends BaseSpec
    with BeforeAndAfterEach {

  val page3HeightView: Page3HeightView = inject[Page3HeightView]

  val cache: AsyncCacheApi = mock[AsyncCacheApi]

  def controller =
    new Page3HowTallAreYouController(
      page3HeightView,
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

  "Page3HowTallAreYouController" when {
    "howTallAreYouPageLoad method" must {
      "open Page3HeightView" in {
        val request: Future[Result] =
          controller.howTallAreYouPageLoad()(FakeRequest())

        status(request) mustBe OK
        contentAsString(request) must include(
          messages.apply("page3.height.title")(Lang.defaultLang)
        )
      }
    }

    // TODO - Add height validation

    "howTallAreYouOnSubmit method" must {
      "redirect to Page4CurrentWeightView with the value set in the cache when a height is inputted" in {
        val result: Future[Result] =
          controller.howTallAreYouOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("howTallAreYou", 2.00.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/how-much-do-you-weigh"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "not continue to next page with a bad request status if nothing is selected on submit" in {
        val result: Future[Result] =
          controller.howTallAreYouOnSubmit()(
            FakeRequest()
          )

        status(result) mustBe BAD_REQUEST
      }
    }
  }
}
