package controllers

import com.google.inject.Inject
import dto.MacroCalcDto
import errors.{CustomErrorHandler, CustomNoContentResponse}
import helpers.{ActivityLevel, MaleOrFemale}
import models.{MacroStat, PreviousWeight}
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import services.{MacroStatService, PreviousWeightService}

import java.time.LocalDate
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.{Duration, SECONDS}

class Page13FullSummaryController @Inject() (
    cache: AsyncCacheApi,
    macroStatService: MacroStatService,
    previousWeightService: PreviousWeightService,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
)(implicit ec: ExecutionContext)
    extends AbstractController(cc)
    with I18nSupport {

  def fullSummaryPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      println(cacheToDto)
      cacheToDto match {
        case Some(value) =>
          println("1" * 100)
          println(value)
          Ok(views.html.Page13FullSummary(value))
        case None =>
          println("2" * 100)
          Ok(views.html.LandingPage())
      }
    }

  def fullSummarySaveData(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      cacheToDto match {
        case Some(data) =>
          val macroStat: MacroStat = MacroStat(
            Some(LocalDate.now()),
            data.activityLevel,
            data.setGoal,
            data.proteinPreference,
            data.fatPreference,
            data.carbPreference,
            data.bodyFat,
            Some("Default"),
            1000,
            500,
            10
          )
          macroStatService
            .addMacroStat(
              "Calvin", // TODO Change name to username
              macroStat
            )
            .flatMap {
              case Left(error) =>
                println("1" * 100)
                println(error)
                println("1" * 100)

                Future.successful(InternalServerError(views.html.LandingPage()))
              case Right(value) =>
                previousWeightService
                  .addPreviousWeight(
                    "Calvin",
                    data.currentWeight
                  )
                  .flatMap {
                    case Right(value) => Future.successful(Ok(value.toString))
                    case Left(CustomNoContentResponse) =>
                      println("2" * 100)
                      Future.successful(NoContent)
                    case _ =>
                      println("3" * 100)
                      Future.successful(
                        InternalServerError(views.html.LandingPage())
                      )
                  }
            }
        case None =>
          Future.successful(InternalServerError(views.html.LandingPage()))
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
