package forms

import play.api.data.Form
import play.api.data.Forms.{date, localDate, mapping, number}

import java.time.LocalDate

case class WhenWereYouBornForm(age: LocalDate)

object WhenWereYouBornForm {

  def form(errorMessageKey: Option[String] = None): Form[WhenWereYouBornForm] =
    Form(
      mapping(
        "howOldAreYou" -> localDate
//          .verifying(
//            error = "Please enter your Page2Age using the Slider or the text box",
//            constraint = e => e < 1 && e > 130
//          )
      )(WhenWereYouBornForm.apply)(WhenWereYouBornForm.unapply)
    )
}
