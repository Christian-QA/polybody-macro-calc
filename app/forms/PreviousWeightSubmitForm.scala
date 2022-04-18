package forms

import play.api.data.Form
import play.api.data.Forms.{boolean, mapping}

case class PreviousWeightSubmitForm(save: Boolean)

object PreviousWeightSubmitForm {

  def form(
      errorMessageKey: Option[String] = None
  ): Form[PreviousWeightSubmitForm] =
    Form(
      mapping(
        "wouldYouLikeToSave" -> boolean
      )(PreviousWeightSubmitForm.apply)(PreviousWeightSubmitForm.unapply)
    )
}
