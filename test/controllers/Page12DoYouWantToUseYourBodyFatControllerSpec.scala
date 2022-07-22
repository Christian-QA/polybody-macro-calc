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
import views.html.{Page11CarbGoalView, Page12BodyFatView}

import scala.concurrent.Future

class Page12DoYouWantToUseYourBodyFatControllerSpec
    extends BaseSpec
    with BeforeAndAfterEach {

  val page12BodyFatView: Page12BodyFatView =
    inject[Page12BodyFatView]

  val cache: AsyncCacheApi = mock[AsyncCacheApi]

  def controller =
    new Page12DoYouWantToUseYourBodyFatController(
      page12BodyFatView,
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

  "Page12DoYouWantToUseYourBodyFatController" when {
    "doYouWantToUseYourBodyFatPageLoad method" must {
      "open Page12BodyFatView" in {
        val request: Future[Result] =
          controller.doYouWantToUseYourBodyFatPageLoad()(FakeRequest())

        status(request) mustBe OK
        contentAsString(request) must include(
          messages.apply("page12.body-fat.title")(Lang.defaultLang)
        )
      }
    }

    // TODO - Add boolean to decide if value is present or not, with validation to enforce value if true

    "doYouWantToUseYourBodyFatOnSubmit method" must {
      "redirect to Page13FullSummaryView with the value set in the cache when a weight is inputted" in {
        val result: Future[Result] =
          controller.doYouWantToUseYourBodyFatOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("doYouWantToUseYourBodyFat", 250.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/full-summary"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "not continue to next page with a bad request status if nothing is selected on submit" in {
        val result: Future[Result] =
          controller.doYouWantToUseYourBodyFatOnSubmit()(
            FakeRequest()
          )

        status(result) mustBe BAD_REQUEST
      }
    }
  }
}
