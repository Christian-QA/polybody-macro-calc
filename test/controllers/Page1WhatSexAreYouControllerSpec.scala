package controllers

import akka.Done
import helpers.{Female, Male, Intersex}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{reset, times, verify, when}
import org.scalatest.BeforeAndAfterEach
import play.api.cache.AsyncCacheApi
import play.api.http.Status.{BAD_REQUEST, OK, SEE_OTHER}
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
import views.html.Page1SexView

import scala.concurrent.Future

class Page1WhatSexAreYouControllerSpec
    extends BaseSpec
    with BeforeAndAfterEach {

  val page1SexView: Page1SexView = inject[Page1SexView]

  val cache: AsyncCacheApi = mock[AsyncCacheApi]

  def controller =
    new Page1WhatSexAreYouController(
      page1SexView,
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

  "Page1WhatSexAreYouController" when {
    "whatSexAreYouPageLoad method" must {
      "open Page1SexView" in {
        val request: Future[Result] =
          controller.whatSexAreYouPageLoad()(FakeRequest())

        status(request) mustBe OK
        contentAsString(request) must include(
          messages.apply("page1.sex.title")(Lang.defaultLang)
        )
      }
    }

    "whatSexAreYouOnSubmit method" must {
      "redirect to Page2AgeView with the sex set in the cache when Male is selected" in {
        val result: Future[Result] =
          controller.whatSexAreYouOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("whatSexAreYou", Male.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/how-old-are-you"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "redirect to Page2AgeView with the sex set in the cache when Female is selected" in {
        val result: Future[Result] =
          controller.whatSexAreYouOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("whatSexAreYou", Female.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/how-old-are-you"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "redirect to Page2AgeView with the sex set in the cache when Intersex is selected" in {
        val result: Future[Result] =
          controller.whatSexAreYouOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("whatSexAreYou", Intersex.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/how-old-are-you"
        )
        verify(cache, times(1)).set(any(), any(), any())
      }
      "not continue to next page with a bad request status if nothing is selected on submit" in {
        val result: Future[Result] =
          controller.whatSexAreYouOnSubmit()(
            FakeRequest()
          )

        status(result) mustBe BAD_REQUEST
      }
    }
  }
}
