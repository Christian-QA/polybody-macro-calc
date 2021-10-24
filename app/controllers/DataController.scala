package controllers

import com.google.inject.Inject
import play.api.Logging
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.{PreviousWeightService, UserService}

import scala.concurrent.ExecutionContext

class DataController @Inject()(userService: UserService, previousWeightService: PreviousWeightService, cc: ControllerComponents)(implicit val ec: ExecutionContext) extends BaseController with Logging {
  override protected def controllerComponents: ControllerComponents = cc

  def getUserDetails(username: String): Action[AnyContent] = Action.async { implicit request =>

    val result = userService.getUserDetails(username)

    result.map { r =>
      println(r)
      Ok(r.toString)
    }


  }

  def getPreviousWeights(username: String): Action[AnyContent] = Action.async { implicit request =>

    val result = previousWeightService.getPreviousWeights(username)

    result.map { r =>
      println(r)
      Ok(r.toString)
    }


  }
}