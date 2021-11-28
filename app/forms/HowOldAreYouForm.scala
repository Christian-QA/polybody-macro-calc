package forms

import helpers.{Female, Male, MaleOrFemale, Other}
import play.api.data.Form
import play.api.data.Forms.{mapping, number, text}
import play.api.libs.json.{
  JsError,
  JsPath,
  JsString,
  JsSuccess,
  Json,
  JsonValidationError,
  OFormat,
  Reads,
  Writes
}

case class HowOldAreYouForm(age: Int)

object HowOldAreYouForm {

  def form(errorMessageKey: Option[String] = None): Form[HowOldAreYouForm] =
    Form(
      mapping(
        "howOldAreYou" -> number
          .verifying(
            error = "Please enter your Age using the Slider or the text box",
            constraint = e => e > 1 && e < 130
          )
      )(HowOldAreYouForm.apply)(HowOldAreYouForm.unapply)
    )
}
