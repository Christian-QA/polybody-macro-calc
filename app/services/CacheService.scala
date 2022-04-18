package services

import com.google.inject.Inject
import dto.MacroCalcDto
import helpers.{ActivityLevel, MaleOrFemale}
import play.api.cache.AsyncCacheApi

import java.time.LocalDate
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, SECONDS}

class CacheService @Inject() (cache: AsyncCacheApi) {

  def cacheToWeightDouble: Option[Double] = {
    Await.result(
      cache.get[Double]("currentWeight"),
      Duration(5, SECONDS)
    )
  }

  def cacheToShortDto: Option[MacroCalcDto] = {
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

  def cacheToFullDto: Option[MacroCalcDto] = {
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
