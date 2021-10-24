package services

import com.google.inject.Inject
import connectors.PolybodyConnector
import models.{MacroStat, PreviousWeight, User}
import ujson.Value

import java.time.LocalDate
import scala.concurrent.{ExecutionContext, Future}

class UserService @Inject()(polybodyConnector: PolybodyConnector)(implicit ec: ExecutionContext) {

  def getUserDetails(username: String) = {
    val data = polybodyConnector.getUserDetails(username)

    data.map{
      entry =>
        User(
          entry(0)("_id").str,
          entry(0)("username").str,
          entry(0)("email").str,
          entry(0)("age").num.toInt,
          entry(0)("gender").str,
          entry(0)("height").num,
          entry(0)("targetWeight").numOpt
        )
    }
  }
}
