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
import views.html.{Page4CurrentWeightView, Page5TargetWeightView}

import scala.concurrent.Future

class Page5WhatIsYourTargetWeightControllerSpec
    extends BaseSpec
    with BeforeAndAfterEach {

  val page5TargetWeightView: Page5TargetWeightView =
    inject[Page5TargetWeightView]

  val cache: AsyncCacheApi = mock[AsyncCacheApi]

  def controller =
    new Page5WhatIsYourTargetWeightController(
      page5TargetWeightView,
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

  "Page5WhatIsYourTargetWeightController" when {
    "whatIsYourTargetWeightPageLoad method" must {
      "open Page5TargetWeightView" in {
        val request: Future[Result] =
          controller.whatIsYourTargetWeightPageLoad()(FakeRequest())

        status(request) mustBe OK
        contentAsString(request) must include(
          messages.apply("page5.target-weight.title")(Lang.defaultLang)
        )
      }
    }

    // TODO - Add weight validation

    "whatIsYourTargetWeightOnSubmit method" must {
      "redirect to Page6ActivityLevelView with the value set in the cache when a height is inputted" in {
        val result: Future[Result] =
          controller.whatIsYourTargetWeightOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("whatIsYourTargetWeight", 200.00.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/how-active-are-you"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "not continue to next page with a bad request status if nothing is selected on submit" in {
        val result: Future[Result] =
          controller.whatIsYourTargetWeightOnSubmit()(
            FakeRequest()
          )

        status(result) mustBe BAD_REQUEST
      }
    }
  }
}
