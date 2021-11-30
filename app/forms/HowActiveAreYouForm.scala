package forms

import helpers._
import play.api.data.Form
import play.api.data.Forms.{mapping, text}
import play.api.libs.json._

case class HowActiveAreYouForm(activity: ActivityLevel)

object HowActiveAreYouForm {

  implicit val formats: OFormat[HowActiveAreYouForm] = {
    implicit val howActiveAreYouReads: Reads[ActivityLevel] = {
      case JsString("sedentary")        => JsSuccess(Sedentary)
      case JsString("lightlyActive")    => JsSuccess(LightlyActive)
      case JsString("moderatelyActive") => JsSuccess(ModeratelyActive)
      case JsString("veryActive")       => JsSuccess(VeryActive)
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
      case Sedentary        => JsString("sedentary")
      case LightlyActive    => JsString("lightlyActive")
      case ModeratelyActive => JsString("moderatelyActive")
      case VeryActive       => JsString("veryActive")
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
