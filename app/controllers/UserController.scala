package controllers

import akka.http.scaladsl.model.HttpResponse
import com.google.inject.Inject
import connectors.PolybodyConnector
import models.User
import play.api.Logging
import play.api.http.Writeable
import play.api.http.Writeable.wByteArray
import play.api.libs.json.{JsArray, Json, OFormat}
import play.api.mvc.Results.Ok
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

class UserController @Inject()(polybodyConnector: PolybodyConnector, cc: ControllerComponents)(implicit val ec: ExecutionContext) extends BaseController with Logging {
  override protected def controllerComponents: ControllerComponents = cc

  def getUserDetails(username: String): Action[AnyContent] = Action.async { implicit request =>

    val result = polybodyConnector.getUserDetails(username)

    result.map { r =>
      println(r)
      Ok(r.toString)
    }


  }
}