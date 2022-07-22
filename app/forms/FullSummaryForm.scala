package forms

import play.api.data.Form
import play.api.data.Forms.{boolean, mapping}

case class FullSummaryForm(save: Boolean)

object FullSummaryForm {

  def form(errorMessageKey: Option[String] = None): Form[FullSummaryForm] =
    Form(
      mapping(
        "wouldYouLikeToSave" -> boolean
      )(FullSummaryForm.apply)(FullSummaryForm.unapply)
    )
}
