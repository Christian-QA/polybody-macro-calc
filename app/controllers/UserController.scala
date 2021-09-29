package controllers

import com.google.inject.Inject
import play.api.Logging
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.PolybodyService

import scala.concurrent.ExecutionContext

class UserController @Inject()(polybodyService: PolybodyService, cc: ControllerComponents)(implicit val ec: ExecutionContext) extends BaseController with Logging {
  override protected def controllerComponents: ControllerComponents = cc

  def getUserDetails(username: String): Action[AnyContent] = Action.async { implicit request =>

    val result = polybodyService.getUserDetails(username)

    result.map { r =>
      println(r)
      Ok(r.toString)
    }


  }
}