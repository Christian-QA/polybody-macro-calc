package services

import com.google.inject.Inject
import connectors.PolybodyConnector
import models.{MacroStat, PreviousWeight, User}
import ujson.Value

import java.time.LocalDate
import scala.concurrent.{ExecutionContext, Future}

class UserService @Inject()(polybodyConnector: PolybodyConnector)(implicit ec: ExecutionContext) {

  def getUserDetails(username: String): Future[User] = {
    val data = polybodyConnector.getUserDetails(username)

    data.map {
      case Right(value) => User(
        value(0)("_id").str,
        value(0)("username").str,
        value(0)("email").str,
        value(0)("age").num.toInt,
        value(0)("gender").str,
        value(0)("height").num,
        value(0)("targetWeight").numOpt
      )
      case Left(_) => User("611be0d7e17315ce09335455", "testUser", "testUser@gmail.com", 25,"male", 190d, Some(140d))
    }
  }
}
