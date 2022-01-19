package forms

import play.api.data.{Form, Forms}
import play.api.data.Forms.{mapping, number}
import play.api.data.format.Formats.doubleFormat

//TODO - Make Optional
case class DoYouWantToUseYourBodyFatForm(bodyFat: Double)

object DoYouWantToUseYourBodyFatForm {

  def form(
      errorMessageKey: Option[String] = None
  ): Form[DoYouWantToUseYourBodyFatForm] =
    Form(
      mapping(
        "doYouWantToUseYourBodyFat" -> Forms.of[Double]
        //          .verifying(
        //            error = "Please enter your Age using the Slider or the text box",
        //            constraint = e => e < 1 && e > 130
        //          )
      )(DoYouWantToUseYourBodyFatForm.apply)(
        DoYouWantToUseYourBodyFatForm.unapply
      )
    )
}
