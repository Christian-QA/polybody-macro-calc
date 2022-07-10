package controllers

import akka.Done
import helpers.{LightlyActive, ModeratelyActive, Sedentary, VeryActive}
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
import views.html.{Page5TargetWeightView, Page6ActivityLevelView}

import scala.concurrent.Future

class Page6HowActiveAreYouControllerSpec
    extends BaseSpec
    with BeforeAndAfterEach {

  val page6ActivityLevelView: Page6ActivityLevelView =
    inject[Page6ActivityLevelView]

  val cache: AsyncCacheApi = mock[AsyncCacheApi]

  def controller =
    new Page6HowActiveAreYouController(
      page6ActivityLevelView,
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

  "Page6HowActiveAreYouController" when {
    "howActiveAreYouPageLoad method" must {
      "open Page6ActivityLevelView" in {
        val request: Future[Result] =
          controller.howActiveAreYouPageLoad()(FakeRequest())

        status(request) mustBe OK
        contentAsString(request) must include(
          messages.apply("page6.activity-level.title")(Lang.defaultLang)
        )
      }
    }

    "whatIsYourTargetWeightOnSubmit method" must {
      "redirect to Page7ShortSummaryView with the value set in the cache when Sedentary is selected" in {
        val result: Future[Result] =
          controller.howActiveAreYouOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("howActiveAreYou", Sedentary.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/short-summary"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "redirect to Page7ShortSummaryView with the value set in the cache when LightlyActive is selected" in {
        val result: Future[Result] =
          controller.howActiveAreYouOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("howActiveAreYou", LightlyActive.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/short-summary"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "redirect to Page7ShortSummaryView with the value set in the cache when ModeratelyActive is selected" in {
        val result: Future[Result] =
          controller.howActiveAreYouOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("howActiveAreYou", ModeratelyActive.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/short-summary"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "redirect to Page7ShortSummaryView with the value set in the cache when VeryActive is selected" in {
        val result: Future[Result] =
          controller.howActiveAreYouOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("howActiveAreYou", VeryActive.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/short-summary"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "not continue to next page with a bad request status if nothing is selected on submit" in {
        val result: Future[Result] =
          controller.howActiveAreYouOnSubmit()(
            FakeRequest()
          )

        status(result) mustBe BAD_REQUEST
      }
    }
  }
}
