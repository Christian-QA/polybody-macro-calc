package dto

import helpers.{ActivityLevel, MaleOrFemale}

import java.time.LocalDate

case class MacroCalcDto(
    sex: MaleOrFemale,
    dob: LocalDate,
    height: Double,
    currentWeight: Double,
    targetWeight: Double,
    activityLevel: ActivityLevel,
    kcalGoal: Int,
    proteinPreference: Option[Int] = None,
    fatPreference: Option[Int] = None,
    carbPreference: Option[Int] = None,
    equationPreference: Option[String] = None,
    bodyFat: Option[Double] = None
)
