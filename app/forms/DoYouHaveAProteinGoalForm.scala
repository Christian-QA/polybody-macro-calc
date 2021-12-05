package forms

import play.api.data.Form
import play.api.data.Forms.{mapping, number}

//TODO - Make Optional
case class DoYouHaveAProteinGoalForm(protein: Int)

object DoYouHaveAProteinGoalForm {

  def form(
      errorMessageKey: Option[String] = None
  ): Form[DoYouHaveAProteinGoalForm] =
    Form(
      mapping(
        "proteinGoal" -> number
        //          .verifying(
        //            error = "Please enter your Age using the Slider or the text box",
        //            constraint = e => e < 1 && e > 130
        //          )
      )(DoYouHaveAProteinGoalForm.apply)(DoYouHaveAProteinGoalForm.unapply)
    )
}
