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
    setGoal: Option[Int],
    proteinPreference: Option[Int],
    fatPreference: Option[Int],
    carbPreference: Option[Int],
    bodyFat: Option[Double]
)
