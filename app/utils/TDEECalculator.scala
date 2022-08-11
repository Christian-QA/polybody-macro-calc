package utils

import com.google.inject.Inject
import helpers.{
  ActivityLevel,
  Female,
  LightlyActive,
  Male,
  MaleOrFemale,
  ModeratelyActive,
  Other,
  Sedentary,
  VeryActive
}

import java.time.LocalDate

class TDEECalculator @Inject() () {

  private def dobToAge(dob: LocalDate): Int =
    LocalDate.now.getYear - dob.getYear

  def bmrKatchMcArdleCalculator(currentWeight: Double, bodyFat: Double): Int = {
    val leanBodyMass: Double = (currentWeight * (100 - bodyFat)) / 100
    val bmr: Double = 370 + (21.6 * leanBodyMass)

    bmr.floor.toInt
  }

  def bmrMifflinStJeorCalculator(
      currentWeight: Double,
      height: Double,
      dob: LocalDate,
      sex: MaleOrFemale
  ): Int = {
    val sexOffset: Int = sex match {
      case Male   => 5
      case Female => -161
      case Other  => -78
    }

    val bmr: Double =
      10 * currentWeight + 6.25 * height - 5 * dobToAge(dob) + sexOffset
    bmr.floor.toInt
  }

  def activityFactor(bmr: Int, activityLevel: ActivityLevel): Int = {
    val activityLevelFactor: Double = activityLevel match {
      case Sedentary        => 1.2
      case LightlyActive    => 1.375
      case ModeratelyActive => 1.55
      case VeryActive       => 1.75
    }
    (bmr * activityLevelFactor).floor.toInt
  }
}
