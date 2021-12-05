package forms

import play.api.data.Form
import play.api.data.Forms.{mapping, number}

//TODO - Make Optional
case class DoYouHaveAKcalGoalForm(kcals: Int)

object DoYouHaveAKcalGoalForm {

  def form(
      errorMessageKey: Option[String] = None
  ): Form[DoYouHaveAKcalGoalForm] =
    Form(
      mapping(
        "kcalGoal" -> number
        //          .verifying(
        //            error = "Please enter your Age using the Slider or the text box",
        //            constraint = e => e < 1 && e > 130
        //          )
      )(DoYouHaveAKcalGoalForm.apply)(DoYouHaveAKcalGoalForm.unapply)
    )
}
