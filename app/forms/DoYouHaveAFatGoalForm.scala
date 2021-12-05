package forms

import play.api.data.Form
import play.api.data.Forms.{mapping, number}

//TODO - Make Optional
case class DoYouHaveAFatGoalForm(fat: Int)

object DoYouHaveAFatGoalForm {

  def form(
      errorMessageKey: Option[String] = None
  ): Form[DoYouHaveAFatGoalForm] =
    Form(
      mapping(
        "doYouHaveAFatGoal" -> number
        //          .verifying(
        //            error = "Please enter your Age using the Slider or the text box",
        //            constraint = e => e < 1 && e > 130
        //          )
      )(DoYouHaveAFatGoalForm.apply)(DoYouHaveAFatGoalForm.unapply)
    )
}
