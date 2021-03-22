package models

import java.time.format.DateTimeParseException
import org.joda.time.DateTime
import play.api.libs.json
import play.api.libs.json._


case class MacroStat(
                      dateTime: DateTime,
                      activityLevel: String,
                      setGoal: Double,
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

    override def reads(json: JsValue): JsResult[DateTime] = json match {
      case JsString(s) =>
        try {
          JsSuccess(DateTime.parse(s))
        } catch {
          case _: DateTimeParseException => JsError("That's not a date")
        }
      case _ => JsError("That's not a date")
    }
  }

  implicit val formats: OFormat[MacroStat] = Json.format[MacroStat]

}