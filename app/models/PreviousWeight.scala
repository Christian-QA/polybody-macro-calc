package models


import java.time.format.DateTimeParseException

import org.joda.time.DateTime
import play.api.libs.json
import play.api.libs.json.{Format, JsError, JsResult, JsString, JsSuccess, JsValue, Json}

case class PreviousWeight(dateTime: DateTime, weight: Double)

object PreviousWeight {

  implicit val dateTimeFormat = new Format[DateTime] {
    override def writes(o: DateTime): JsValue = json.JsString(o.toString())

    override def reads(json: JsValue): JsResult[DateTime] = json match {
      case JsString(s) =>
        try {
          JsSuccess(DateTime.parse(s))
        } catch {
          case _: DateTimeParseException => JsError("That's not a date")
        }
    }
  }

  implicit val formats = Json.format[PreviousWeight]


}