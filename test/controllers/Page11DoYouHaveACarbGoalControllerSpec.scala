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
import views.html.{Page10FatGoalView, Page9ProteinGoalView}

import scala.concurrent.Future

class Page10DoYouHaveAFatGoalControllerSpec
    extends BaseSpec
    with BeforeAndAfterEach {

  val page10FatGoalView: Page10FatGoalView =
    inject[Page10FatGoalView]

  val cache: AsyncCacheApi = mock[AsyncCacheApi]

  def controller =
    new Page10DoYouHaveAFatGoalController(
      page10FatGoalView,
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

  "Page10DoYouHaveAFatGoalController" when {
    "doYouHaveAFatGoalPageLoad method" must {
      "open Page10FatGoalView" in {
        val request: Future[Result] =
          controller.doYouHaveAFatGoalPageLoad()(FakeRequest())

        status(request) mustBe OK
        contentAsString(request) must include(
          messages.apply("page10.fat-goal.title")(Lang.defaultLang)
        )
      }
    }

    // TODO - Add boolean to decide if value is present or not, with validation to enforce value if true

    "doYouHaveAFatGoalOnSubmit method" must {
      "redirect to Page11CarbGoalView with the value set in the cache when a weight is inputted" in {
        val result: Future[Result] =
          controller.doYouHaveAFatGoalOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("doYouHaveAFatGoal", 50.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/do-you-have-a-carb-goal"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "not continue to next page with a bad request status if nothing is selected on submit" in {
        val result: Future[Result] =
          controller.doYouHaveAFatGoalOnSubmit()(
            FakeRequest()
          )

        status(result) mustBe BAD_REQUEST
      }
    }
  }
}
