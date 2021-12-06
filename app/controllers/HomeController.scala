package controllers

import com.google.inject.Inject
import play.api.i18n.I18nSupport
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */

class HomeController @Inject() (
    cc: ControllerComponents,
    messagesControllerComponents: MessagesControllerComponents
) extends AbstractController(cc)
    with I18nSupport {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.Index())
    }

}
