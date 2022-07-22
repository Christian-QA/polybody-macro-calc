package forms

import play.api.data.Form
import play.api.data.Forms.{boolean, mapping}

case class PreviousWeightForm(save: Boolean)

object PreviousWeightForm {

  def form(
      errorMessageKey: Option[String] = None
  ): Form[PreviousWeightForm] =
    Form(
      mapping(
        "wouldYouLikeToSave" -> boolean
      )(PreviousWeightForm.apply)(PreviousWeightForm.unapply)
    )
}
