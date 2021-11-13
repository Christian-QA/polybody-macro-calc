package services

import com.google.inject.Inject
import connectors.PolybodyConnector
import errors.CustomErrorHandler
import models.{MacroStat, User}

import java.time.LocalDate
import scala.concurrent.{ExecutionContext, Future}

class MacroStatService @Inject()(polybodyConnector: PolybodyConnector)(implicit ec: ExecutionContext) {

  def getMacroStats(username: String): Future[Either[CustomErrorHandler, MacroStat]] = {
    val data = polybodyConnector.getMacroStats(username)

    data.map {
      case Right(value) =>
        Right(
          MacroStat(
            LocalDate.parse(value(0)("dateTime").str),
            value(0)("activityLevel").str,
            value(0)("setGoal").num,
            Some(value(0)("proteinPreference").num.toInt),
            Some(value(0)("fatPreference").num.toInt),
            Some(value(0)("carbPreference").num.toInt),
            Some(value(0)("bodyFat").num.toInt),
            value(0)("equationPreference").strOpt,
            value(0)("maintenanceCalories").num.toInt,
            value(0)("targetCalories").num.toInt,
            value(0)("timeToGoal").num.toInt
          )
        )
      case Left(value) => Left(value)
    }
  }
}
