package controllers

import com.google.inject.Inject
import dto.MacroCalcDto
import forms.ShortSummaryForm
import helpers.{ActivityLevel, MaleOrFemale}
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._

import java.time.LocalDate
import scala.concurrent.duration.{Duration, SECONDS}
import scala.concurrent.{Await, Future}

class Page7ShortSummaryController @Inject() (
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def shortSummaryPageLoad(): Action[AnyContent] = {
    Action { implicit request: Request[AnyContent] =>
      println(cacheToDto)
      cacheToDto match {
        case Some(value) =>
          println("1" * 100)
          println(value)
          Ok(views.html.Page7ShortSummary(ShortSummaryForm.form(), value))
        case None =>
          println("2" * 100)
          Ok(views.html.Index())

        //BadRequest(views.html.errorViews.BadRequestView)
      }
    }
  }

  def shortSummaryOnSubmit(): Action[AnyContent] =
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

  private def cacheToDto: Option[MacroCalcDto] = {
    val sex: Option[MaleOrFemale] = {
      Await.result(
        cache.get[MaleOrFemale]("sex"),
        Duration(5, SECONDS)
      )
    }

    val age: Option[LocalDate] =
      Await.result(
        cache.get[LocalDate]("age"),
        Duration(5, SECONDS)
      )

    val height: Option[Double] =
      Await.result(
        cache.get[Double]("height"),
        Duration(5, SECONDS)
      )

    val currentWeight: Option[Double] =
      Await.result(
        cache.get[Double]("currentWeight"),
        Duration(5, SECONDS)
      )

    val targetWeight: Option[Double] =
      Await.result(
        cache.get[Double]("targetWeight"),
        Duration(5, SECONDS)
      )

    val activityLevel: Option[ActivityLevel] =
      Await.result(
        cache.get[ActivityLevel]("activityLevel"),
        Duration(5, SECONDS)
      )

    for {
      sex <- sex
      age <- age
      height <- height
      currentWeight <- currentWeight
      targetWeight <- targetWeight
      activityLevel <- activityLevel
    } yield {
      MacroCalcDto(
        sex,
        age,
        height,
        currentWeight,
        targetWeight,
        activityLevel
      )
    }
  }
}
