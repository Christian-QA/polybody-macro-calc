package controllers

import com.google.inject.Inject
import errors.CustomNoContentResponse
import play.api.Logging
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.{PreviousWeightService, UserService}

import scala.concurrent.ExecutionContext

///Todo - This class is more to live test the routes than provide purpose in production. This will be deleted after the Views have been made
class DataController @Inject()(userService: UserService, previousWeightService: PreviousWeightService, cc: ControllerComponents)(implicit val ec: ExecutionContext) extends BaseController with Logging {
  override protected def controllerComponents: ControllerComponents = cc

  def getUserDetails(username: String): Action[AnyContent] = Action.async { implicit request =>

    val result = userService.getUserDetails(username)

    result.map {
      case Right(value) => Ok(value.toString)
      case Left(CustomNoContentResponse) => NoContent
      case _ => InternalServerError
    }


  }

  def getPreviousWeights(username: String): Action[AnyContent] = Action.async { implicit request =>

    val result = previousWeightService.getPreviousWeights(username)

    result.map {
      case Right(value) => Ok(value.toString)
      case Left(CustomNoContentResponse) => NoContent
      case _ => InternalServerError
    }
  }
}