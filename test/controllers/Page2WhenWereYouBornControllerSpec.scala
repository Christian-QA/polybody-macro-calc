package controllers

import akka.Done
import helpers.{Female, Male}
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
import views.html.{Page1SexView, Page2AgeView}

import java.time.LocalDate
import scala.concurrent.Future

class Page2WhenWereYouBornControllerSpec
    extends BaseSpec
    with BeforeAndAfterEach {

  val page2AgeView: Page2AgeView = inject[Page2AgeView]

  val cache: AsyncCacheApi = mock[AsyncCacheApi]

  def controller =
    new Page2WhenWereYouBornController(
      page2AgeView,
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

  "Page2WhenWereYouBornController" when {
    "whenWereYouBornPageLoad method" must {
      "open page1SexView" in {
        val request: Future[Result] =
          controller.whenWereYouBornPageLoad()(FakeRequest())

        status(request) mustBe OK
        contentAsString(request) must include(
          messages.apply("page2.age.title")(Lang.defaultLang)
        )
      }
    }

    // TODO - Add age validation

    "whenWereYouBornOnSubmit method" must {
      "redirect to page2WhenWereYouBornView with the sex set in the cache when Female is selected" in {
        val result: Future[Result] =
          controller.whenWereYouBornOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("howOldAreYou", LocalDate.now.minusYears(20).toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/how-tall-are-you"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "not continue to next page with a bad request status if nothing is selected on submit" in {
        val result: Future[Result] =
          controller.whenWereYouBornOnSubmit()(
            FakeRequest()
          )

        status(result) mustBe BAD_REQUEST
      }
    }
  }
}
