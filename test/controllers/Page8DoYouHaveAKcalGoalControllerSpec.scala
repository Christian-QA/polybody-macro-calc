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
import views.html.{Page4CurrentWeightView, Page8KcalGoalView}

import scala.concurrent.Future

class Page8DoYouHaveAKcalGoalControllerSpec
    extends BaseSpec
    with BeforeAndAfterEach {

  val page8KcalGoalView: Page8KcalGoalView =
    inject[Page8KcalGoalView]

  val cache: AsyncCacheApi = mock[AsyncCacheApi]

  def controller =
    new Page8DoYouHaveAKcalGoalController(
      page8KcalGoalView,
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

  "Page8DoYouHaveAKcalGoalController" when {
    "doYouHaveAKcalGoalPageLoad method" must {
      "open Page8KcalGoalView" in {
        val request: Future[Result] =
          controller.doYouHaveAKcalGoalPageLoad()(FakeRequest())

        status(request) mustBe OK
        contentAsString(request) must include(
          messages.apply("page8.kcal-goal.title")(Lang.defaultLang)
        )
      }
    }

    // TODO - Add boolean to decide if value is present or not, with validation to enforce value if true

    "doYouHaveAKcalGoalOnSubmit method" must {
      "redirect to Page9ProteinGoalView with the value set in the cache when a weight is inputted" in {
        val result: Future[Result] =
          controller.doYouHaveAKcalGoalOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("doYouHaveAKcalGoal", 2000.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/do-you-have-a-protein-goal"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "not continue to next page with a bad request status if nothing is selected on submit" in {
        val result: Future[Result] =
          controller.doYouHaveAKcalGoalOnSubmit()(
            FakeRequest()
          )

        status(result) mustBe BAD_REQUEST
      }
    }
  }
}
