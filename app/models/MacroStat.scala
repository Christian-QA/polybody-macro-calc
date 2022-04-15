package models

import forms.HowActiveAreYouForm
import helpers.{
  ActivityLevel,
  LightlyActive,
  ModeratelyActive,
  Sedentary,
  VeryActive
}

import java.time.format.DateTimeParseException
import org.joda.time.DateTime
import play.api.libs.json
import play.api.libs.json._

import java.time.LocalDate

case class MacroStat(
    dateTime: Option[LocalDate],
    activityLevel: ActivityLevel,
    setGoal: Option[Int],
    proteinPreference: Option[Int],
    fatPreference: Option[Int],
    carbPreference: Option[Int],
    bodyFat: Option[Double],
    equationPreference: Option[String],
    maintenanceCalories: Int,
    targetCalories: Int,
    timeToGoal: Int
)

object MacroStat {

  implicit val dateTimeFormat: Format[DateTime] = new Format[DateTime] {
    override def writes(o: DateTime): JsValue = json.JsString(o.toString())

    override def reads(json: JsValue): JsResult[DateTime] =
      json match {
        case JsString(s) =>
          try {
            JsSuccess(DateTime.parse(s))
          } catch {
            case _: DateTimeParseException => JsError("That's not a date")
          }
        case _ => JsError("That's not a date")
      }
  }

  implicit val activityLevelFormat: Format[ActivityLevel] =
    new Format[ActivityLevel] {
      override def writes(o: ActivityLevel): JsValue =
        json.JsString(o.toString)

      override def reads(json: JsValue): JsResult[ActivityLevel] =
        json match {
          case JsString("Sedentary")        => JsSuccess(Sedentary)
          case JsString("LightlyActive")    => JsSuccess(LightlyActive)
          case JsString("ModeratelyActive") => JsSuccess(ModeratelyActive)
          case JsString("VeryActive")       => JsSuccess(VeryActive)
          case _                            => JsError("That's not an activity level")
        }
    }

  implicit val formats: OFormat[MacroStat] = Json.format[MacroStat]
}
