package forms

import play.api.data.Form
import play.api.data.Forms.{mapping, number}

//TODO - Make Optional
case class DoYouWantToUseYourBodyFatForm(bodyFat: Int)

object DoYouWantToUseYourBodyFatForm {

  def form(
      errorMessageKey: Option[String] = None
  ): Form[DoYouWantToUseYourBodyFatForm] =
    Form(
      mapping(
        "doYouWantToUseYourBodyFat" -> number
        //          .verifying(
        //            error = "Please enter your Age using the Slider or the text box",
        //            constraint = e => e < 1 && e > 130
        //          )
      )(DoYouWantToUseYourBodyFatForm.apply)(
        DoYouWantToUseYourBodyFatForm.unapply
      )
    )
}
