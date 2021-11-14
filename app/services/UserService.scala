package services

import com.google.inject.Inject
import connectors.PolybodyConnector
import errors.CustomErrorHandler
import models.User

import scala.concurrent.{ExecutionContext, Future}

class UserService @Inject()(polybodyConnector: PolybodyConnector)(implicit ec: ExecutionContext) {

  def getUserDetails(username: String): Future[Either[CustomErrorHandler, User]] = {
    val data = polybodyConnector.getUserDetails(username)

    data.map {
      case Right(value) =>
        Right(
          User(
            value(0)("_id").str,
            value(0)("username").str,
            value(0)("email").str,
            value(0)("age").num.toInt,
            value(0)("gender").str,
            value(0)("height").num,
            value(0)("targetWeight").numOpt
          )
        )
      case Left(value) => Left(value)
    }
  }
}