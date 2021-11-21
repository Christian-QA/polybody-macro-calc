package controllers

import com.google.inject.Inject
import play.api.mvc.{
  AbstractController,
  Action,
  AnyContent,
  ControllerComponents,
  MessagesControllerComponents,
  Request
}

class SexController @Inject() (
    cc: ControllerComponents,
    messagesControllerComponents: MessagesControllerComponents
) extends AbstractController(cc) {

  def whatSexAreYouPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.gender())
    }

  def whatSexAreYouOnSubmit: Action[AnyContent] = Action { implicit request: Request[AnyContent] => {

	}

}
