package forms

import play.api.data.{Form, Forms}
import play.api.data.Forms.{boolean, mapping}

case class ShortSummaryForm(continue: Boolean)

object ShortSummaryForm {

  def form(errorMessageKey: Option[String] = None): Form[ShortSummaryForm] =
    Form(
      mapping(
        "doYouWantToUseAdvancedValues" -> boolean
      )(ShortSummaryForm.apply)(ShortSummaryForm.unapply)
    )
}
