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
import views.html.Page11CarbGoalView

import scala.concurrent.Future

class Page11DoYouHaveACarbGoalControllerSpec
    extends BaseSpec
    with BeforeAndAfterEach {

  val page11CarbGoalView: Page11CarbGoalView =
    inject[Page11CarbGoalView]

  val cache: AsyncCacheApi = mock[AsyncCacheApi]

  def controller =
    new Page11DoYouHaveACarbGoalController(
      page11CarbGoalView,
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

  "Page11DoYouHaveACarbGoalController" when {
    "doYouHaveACarbGoalPageLoad method" must {
      "open Page11CarbGoalView" in {
        val request: Future[Result] =
          controller.doYouHaveACarbGoalPageLoad()(FakeRequest())

        status(request) mustBe OK
        contentAsString(request) must include(
          messages.apply("page11.carb-goal.title")(Lang.defaultLang)
        )
      }
    }

    // TODO - Add boolean to decide if value is present or not, with validation to enforce value if true

    "doYouHaveACarbGoalOnSubmit method" must {
      "redirect to Page12BodyFatView with the value set in the cache when a weight is inputted" in {
        val result: Future[Result] =
          controller.doYouHaveACarbGoalOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("doYouHaveACarbGoal", 250.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/do-you-want-to-use-your-body-fat"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "not continue to next page with a bad request status if nothing is selected on submit" in {
        val result: Future[Result] =
          controller.doYouHaveACarbGoalOnSubmit()(
            FakeRequest()
          )

        status(result) mustBe BAD_REQUEST
      }
    } Page12DoYouWantToUseYourBodyFatController
  }
}
