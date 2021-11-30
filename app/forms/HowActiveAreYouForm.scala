package forms

import helpers._
import play.api.data.Form
import play.api.data.Forms.{mapping, text}
import play.api.libs.json._

case class HowActiveAreYouForm(activity: ActivityLevel)

object HowActiveAreYouForm {

  implicit val formats: OFormat[HowActiveAreYouForm] = {
    implicit val howActiveAreYouReads: Reads[ActivityLevel] = {
      case JsString("Sedentary")        => JsSuccess(Sedentary)
      case JsString("LightlyActive")    => JsSuccess(LightlyActive)
      case JsString("ModeratelyActive") => JsSuccess(ModeratelyActive)
      case JsString("VeryActive")       => JsSuccess(VeryActive)
      case _ =>
        JsError(
          Seq(
            JsPath() -> Seq(
              JsonValidationError("error.expected.jsString(activity)")
            )
          )
        )
    }
    implicit val howActiveAreYouWrites: Writes[ActivityLevel] = {
      activityLevel: ActivityLevel => JsString(activityLevel.toString)
    }
    Json.format[HowActiveAreYouForm]
  }

  def form(errorMessageKey: Option[String] = None): Form[HowActiveAreYouForm] =
    Form(
      mapping(
        "howActiveAreYou" -> text
          .verifying(
            error = "Please select your sex to continue with the calculation",
            constraint = e => e.nonEmpty
          )
          .transform[ActivityLevel](
            fromString => ActivityLevel(fromString),
            fromProduct => fromProduct.toString
          )
      )(HowActiveAreYouForm.apply)(HowActiveAreYouForm.unapply)
    )
}
