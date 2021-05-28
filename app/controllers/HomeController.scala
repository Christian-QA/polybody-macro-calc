package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, messagesControllerComponents: MessagesControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def explore() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.explore())
  }
  
  def gender() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.gender())
  }

  def age() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.age())
  }

  def height() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.height())
  }

  def currentWeight() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.current_weight())
  }

  def targetWeight() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.target_weight())
  }
}
