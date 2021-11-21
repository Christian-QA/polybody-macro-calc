package forms

import helpers.{Female, Male, MaleOrFemale, Other}
import play.api.data.Form
import play.api.data.Forms.{mapping, text}
import play.api.libs.json.Json.obj
import play.api.libs.json.{
  JsError,
  JsPath,
  JsResult,
  JsString,
  JsSuccess,
  JsValue,
  Json,
  JsonValidationError,
  OFormat,
  Reads,
  Writes
}

case class WhatSexAreYouForm(sex: MaleOrFemale)

object WhatSexAreYouForm {

  implicit val formats: OFormat[WhatSexAreYouForm] = {
    implicit val whatSexAreYouReads: Reads[MaleOrFemale] = {
      case JsString("male")   => JsSuccess(Male)
      case JsString("female") => JsSuccess(Female)
      case JsString("other")  => JsSuccess(Other)
      case _ =>
        JsError(
          Seq(
            JsPath() -> Seq(
              JsonValidationError("error.expected.jsString(maleOrFemale)")
            )
          )
        )
    }
    implicit val whatSexAreYouWrites: Writes[MaleOrFemale] = {
      case Male   => JsString("male")
      case Female => JsString("female")
      case Other  => JsString("other")
    }
    Json.format[WhatSexAreYouForm]
  }

  val form: Form[WhatSexAreYouForm] = Form(
    mapping(
      "whatSexAreYou" -> text
        .verifying(
          error = "Please select your sex to continue with the calculation",
          constraint = e => e.nonEmpty
        )
        .transform[MaleOrFemale](
          fromString => MaleOrFemale(fromString),
          fromProduct => fromProduct.toString
        )
    )(WhatSexAreYouForm.apply)(WhatSexAreYouForm.unapply)
  )
}
