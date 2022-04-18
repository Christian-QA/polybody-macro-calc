package controllers

import com.google.inject.Inject
import dto.MacroCalcDto
import forms.ShortSummaryForm
import helpers.{ActivityLevel, MaleOrFemale}
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import services.CacheService

import java.time.LocalDate
import scala.concurrent.duration.{Duration, SECONDS}
import scala.concurrent.{Await, Future}

class Page7ShortSummaryController @Inject() (
    cacheService: CacheService,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def shortSummaryPageLoad(): Action[AnyContent] = {
    Action { implicit request: Request[AnyContent] =>
      println(cacheService.cacheToShortDto)
      cacheService.cacheToShortDto match {
        case Some(value) =>
          println("1" * 100)
          println(value)
          Ok(views.html.Page7ShortSummary(ShortSummaryForm.form(), value))
        case None =>
          println("2" * 100)
          Ok(views.html.LandingPage())

        //BadRequest(views.html.errorViews.BadRequestView)
      }
    }
  }

  def shortSummaryOnSubmit(): Action[AnyContent] = {
    Action.async { implicit request: Request[AnyContent] =>
      ShortSummaryForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.LandingPageController.index())),
          value =>
            if (value.continue) {
              Future.successful(
                Redirect(
                  routes.Page8DoYouHaveAKcalGoalController
                    .doYouHaveAKcalGoalPageLoad()
                )
              )
            } else {
              Future.successful(
                Redirect(
                  routes.LandingPageController.index()
                )
              )
            }
        )
    }
  }
}
