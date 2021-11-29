package forms

import play.api.data.{Form, Forms}
import play.api.data.Forms.mapping
import play.api.data.format.Formats.doubleFormat

case class WhatIsYourTargetWeightForm(weight: Double)

object WhatIsYourTargetWeightForm {

  def form(
      errorMessageKey: Option[String] = None
  ): Form[HowMuchDoYouWeighForm] =
    Form(
      mapping(
        "howTallAreYou" -> Forms.of[Double]
      )(HowMuchDoYouWeighForm.apply)(HowMuchDoYouWeighForm.unapply)
    )
}
