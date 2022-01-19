package forms

import play.api.data.Form
import play.api.data.Forms.{mapping, number}

//TODO - Make Optional
case class DoYouHaveACarbGoalForm(carb: Int)

object DoYouHaveACarbGoalForm {

  def form(
      errorMessageKey: Option[String] = None
  ): Form[DoYouHaveACarbGoalForm] =
    Form(
      mapping(
        "doYouHaveACarbGoal" -> number
        //          .verifying(
        //            error = "Please enter your Age using the Slider or the text box",
        //            constraint = e => e < 1 && e > 130
        //          )
      )(DoYouHaveACarbGoalForm.apply)(DoYouHaveACarbGoalForm.unapply)
    )
}
