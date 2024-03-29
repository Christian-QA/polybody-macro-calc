package services

import com.google.inject.Inject
import connectors.PolybodyConnector
import errors.CustomErrorHandler
import helpers.MaleOrFemale
import models.User
import ujson.Value

import java.time.LocalDate
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{ExecutionContext, Future}

class UserService @Inject() (polybodyConnector: PolybodyConnector)(implicit
    ec: ExecutionContext
) {

  def getUserDetails(
      username: String
  ): Future[Either[CustomErrorHandler, User]] = {
    val data: Future[Either[CustomErrorHandler, ArrayBuffer[Value]]] =
      polybodyConnector.getUserDetails(username)

    data.map {
      case Right(value) =>
        Right(
          User(
            value(0)("_id").str,
            value(0)("username").str,
            value(0)("email").str,
            LocalDate.parse(value(0)("dob").str),
            MaleOrFemale.apply(value(0)("sex").str),
            value(0)("height").num,
            value(0)("targetWeight").numOpt
          )
        )
      case Left(value) => Left(value)
    }
  }
}
