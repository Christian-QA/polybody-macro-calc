package controllers.register

import play.api.mvc.{AnyContent, Request}
import views.html.register.gender
import javax.inject._
import play.api.mvc._

class RegisterController @Inject()(cc: ControllerComponents) extends AbstractController(cc){

  def gender(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.register.gender())
  }

  def age(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.age())
  }

  def height(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.register.height())
  }

  def currentWeight(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.register.current_weight())
  }

  def targetWeight(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.register.target_weight())
  }
}
