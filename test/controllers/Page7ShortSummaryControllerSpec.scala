package controllers

import akka.Done
import controllers.handler.ErrorHandler
import dto.MacroCalcDto
import helpers.{Male, ModeratelyActive, Sedentary}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{reset, times, verify, when}
import org.scalatest.BeforeAndAfterEach
import play.api.cache.AsyncCacheApi
import play.api.http.Status.{BAD_REQUEST, OK, REQUEST_TIMEOUT, SEE_OTHER}
import play.api.i18n.{Lang, Langs}
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers.{
  contentAsString,
  defaultAwaitTimeout,
  redirectLocation,
  status
}
import services.CacheService
import utils.BaseSpec
import views.html.Page7ShortSummaryView

import java.time.LocalDate
import scala.concurrent.Future

class Page7ShortSummaryControllerSpec extends BaseSpec with BeforeAndAfterEach {

  val page7ShortSummaryView: Page7ShortSummaryView =
    inject[Page7ShortSummaryView]

  val cacheService: CacheService = mock[CacheService]

  def controller =
    new Page7ShortSummaryController(
      cacheService,
      inject[ErrorHandler],
      page7ShortSummaryView,
      cc,
      messages,
      inject[Langs]
    )

  //TODO - Remember to add test when implementing auth

  "Page7ShortSummaryControllerSpec" when {

    val macroCalcDto = MacroCalcDto(
      Male,
      LocalDate.now.minusYears(20),
      2.00d,
      200.00d,
      150.00d,
      ModeratelyActive,
      None,
      None,
      None,
      None,
      None
    )

    "howActiveAreYouPageLoad method" must {

      when(cacheService.cacheToShortDto).thenReturn(Some(macroCalcDto))

      "open Page7ShortSummaryView when the cache data is successfully retrieved" in {
        val request: Future[Result] =
          controller.shortSummaryPageLoad()(FakeRequest())

        status(request) mustBe OK
        contentAsString(request) must include(
          messages.apply("page7.short-summary.title")(Lang.defaultLang)
        )
      }
      "open ErrorTimeoutView when the cache service returns a CustomTimeoutResponse" in {

        when(cacheService.cacheToShortDto).thenReturn(None)

        val request: Future[Result] =
          controller.shortSummaryPageLoad()(FakeRequest())

        status(request) mustBe REQUEST_TIMEOUT
        contentAsString(request) must include(
          messages.apply("error.timeout.title")(Lang.defaultLang)
        )
      }
    }

    "whatIsYourTargetWeightOnSubmit method" must {
      "redirect to Page8KcalGoalView when true is selected" in {
        val result: Future[Result] =
          controller.shortSummaryOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("howActiveAreYou", Sedentary.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/short-summary"
        )
      }
      "redirect to Page13FullSummary WHAT????" in {
        val result: Future[Result] =
          controller.shortSummaryOnSubmit()(
            FakeRequest().withFormUrlEncodedBody(
              ("howActiveAreYou", Sedentary.toString)
            )
          )

        status(result) mustBe SEE_OTHER
        redirectLocation(result) mustBe Some(
          "/short-summary"
        )
      }

      //TODO - Currently redirects to the last page (same as if true is selected), need to decide on how to verify
//      "not continue to next page with a bad request status if nothing is selected on submit" in {
//        val result: Future[Result] =
//          controller.shortSummaryOnSubmit()(
//            FakeRequest()
//          )
//
//        status(result) mustBe BAD_REQUEST
//      }
    }
  }
}
