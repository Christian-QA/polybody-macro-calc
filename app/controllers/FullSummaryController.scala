package controllers

import com.google.inject.Inject
import dto.MacroCalcDto
import helpers.{ActivityLevel, MaleOrFemale}
import models.{MacroStat, PreviousWeight}
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import services.{MacroStatService, PreviousWeightService}

import java.time.LocalDate
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.{Duration, SECONDS}

class FullSummaryController @Inject() (
    macroStatService: MacroStatService,
    previousWeightService: PreviousWeightService,
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

  def fullSummaryOnSubmit(): Action[AnyContent] = {
    Action.async { implicit request: Request[AnyContent] =>
      val cacheData: Option[MacroCalcDto] = cacheToDto

      val localDate: LocalDate = LocalDate.now()

      cacheData match {
        case Some(value) =>
          val macroStat = MacroStat(
            localDate,
            value.activityLevel,
            value.targetWeight,
            value.proteinPreference,
            value.fatPreference,
            value.carbPreference,
            value.bodyFat,
            value.equationPreference,
            2000,
            value.kcalGoal,
            50
          )

          macroStatService.addMacroStat("", macroStat)
          previousWeightService.addPreviousWeight("", value.currentWeight)

        case None => Future.successful(InternalServerError(""))
      }

      Future.successful(
        Redirect(
          routes.ShortSummaryController
            .shortSummaryPageLoad()
        )
      )
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
      kcalGoal <- kcalGoal
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
        Some(""),
        bodyFat
      )
    }
  }
}
