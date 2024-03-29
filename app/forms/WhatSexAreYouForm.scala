package forms

import helpers.{Female, Male, MaleOrFemale, Other}
import play.api.data.Form
import play.api.data.Forms.{mapping, text}
import play.api.libs.json._

case class WhatSexAreYouForm(sex: MaleOrFemale)

object WhatSexAreYouForm {

  implicit val formats: OFormat[WhatSexAreYouForm] = {
    implicit val whatSexAreYouReads: Reads[MaleOrFemale] = {
      case JsString("Male")   => JsSuccess(Male)
      case JsString("Female") => JsSuccess(Female)
      case JsString("Other")  => JsSuccess(Other)
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
      case Male   => JsString("Male")
      case Female => JsString("Female")
      case Other  => JsString("Other")
    }
    Json.format[WhatSexAreYouForm]
  }

  def form(errorMessageKey: Option[String] = None): Form[WhatSexAreYouForm] =
    Form(
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
