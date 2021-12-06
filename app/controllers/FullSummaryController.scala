package controllers

import com.google.inject.Inject
import dto.MacroCalcDto
import helpers.{ActivityLevel, MaleOrFemale}
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._

import java.time.LocalDate
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, SECONDS}

class FullSummaryController @Inject() (
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def fullSummaryPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      println(cacheToDto)
      cacheToDto match {
        case Some(value) =>
          println("1" * 100)
          println(value)
          Ok(views.html.FullSummary(value))
        case None =>
          println("2" * 100)
          Ok(views.html.Index())

      }
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

    val kcalGoal: Option[Int] =
      Await.result(
        cache.get[Int]("kcalGoal"),
        Duration(5, SECONDS)
      )

    val proteinGoal: Option[Int] =
      Await.result(
        cache.get[Int]("proteinGoal"),
        Duration(5, SECONDS)
      )

    val fatGoal: Option[Int] =
      Await.result(
        cache.get[Int]("fatGoal"),
        Duration(5, SECONDS)
      )

    val carbGoal: Option[Int] =
      Await.result(
        cache.get[Int]("carbGoal"),
        Duration(5, SECONDS)
      )

    val bodyFat: Option[Double] =
      Await.result(
        cache.get[Double]("bodyFat"),
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
        activityLevel,
        kcalGoal,
        proteinGoal,
        fatGoal,
        carbGoal,
        bodyFat
      )
    }
  }
}
