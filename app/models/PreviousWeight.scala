package models

import java.time.format.DateTimeParseException
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

case class PreviousWeight(dateTime: LocalDate, weight: Double)

object PreviousWeight {

  implicit val dateTimeFormat: Format[LocalDate] = new Format[LocalDate] {
    override def writes(o: LocalDate): JsValue = json.JsString(o.toString)

    override def reads(json: JsValue): JsResult[LocalDate] =
      json match {
        case JsString(s) =>
          try {
            JsSuccess(LocalDate.parse(s))
          } catch {
            case _: DateTimeParseException => JsError("That's not a date")
          }
        case _ => JsError("That's not a date")
      }
  }

  implicit val formats: OFormat[PreviousWeight] = Json.format[PreviousWeight]
}
