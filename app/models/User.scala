package models

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
import play.api.libs.json
import play.api.libs.json.{
  Format,
  JsError,
  JsResult,
  JsString,
  JsSuccess,
  JsValue,
  Json,
  OFormat
}

import java.time.LocalDate
import java.time.format.DateTimeParseException

case class User(
    _id: String,
    username: String,
    email: String,
    dob: LocalDate,
    sex: MaleOrFemale,
    height: Double,
    targetWeight: Option[Double]
)

object User {

  implicit val activityLevelFormat: Format[MaleOrFemale] =
    new Format[MaleOrFemale] {
      override def writes(o: MaleOrFemale): JsValue =
        json.JsString(o.toString.toLowerCase)

      override def reads(json: JsValue): JsResult[MaleOrFemale] =
        json match {
          case JsString("male")   => JsSuccess(Male)
          case JsString("female") => JsSuccess(Female)
          case JsString("other")  => JsSuccess(Other)
          case _: DateTimeParseException =>
            JsError("That's not a sex")
          case _ => JsError("That's not a sex")
        }
    }

  implicit val formats: OFormat[User] = Json.format[User]

}
