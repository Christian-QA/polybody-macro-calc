package forms

import play.api.data.Forms.mapping
import play.api.data.format.Formats.doubleFormat
import play.api.data.{Form, Forms}

case class HowTallAreYouForm(height: Double)

object HowTallAreYouForm {

  def form(errorMessageKey: Option[String] = None): Form[HowTallAreYouForm] =
    Form(
      mapping(
        "howTallAreYou" -> Forms.of[Double]
      )(HowTallAreYouForm.apply)(HowTallAreYouForm.unapply)
    )
}
