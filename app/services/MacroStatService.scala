package services

import com.google.inject.Inject
import connectors.PolybodyConnector
import errors.CustomErrorHandler
import models.MacroStat
import ujson.Value

import java.time.LocalDate
import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{ExecutionContext, Future}

class MacroStatService @Inject()(polybodyConnector: PolybodyConnector)(implicit ec: ExecutionContext) {

  def getMacroStatsWeights(username: String): Future[Either[CustomErrorHandler, List[MacroStat]]] = {
    val data: Future[Either[CustomErrorHandler, ArrayBuffer[Value]]] = polybodyConnector.getMacroStats(username)

    data.map {
      case Right(value) =>
        val parsedInput: ArrayBuffer[Value] = value(0).arr

        @tailrec
        def macroStatList(input: List[MacroStat], acc: Int): List[MacroStat] = {
          if (acc < parsedInput.length) {
            println(parsedInput)
            val stats = MacroStat(
              LocalDate.parse(parsedInput(0)("dateTime").str),
              parsedInput(0)("activityLevel").str,
              parsedInput(0)("setGoal").num,
              Some(parsedInput(0)("proteinPreference").num.toInt),
              Some(parsedInput(0)("fatPreference").num.toInt),
              Some(parsedInput(0)("carbPreference").num.toInt),
              Some(parsedInput(0)("bodyFat").num.toInt),
              parsedInput(0)("equationPreference").strOpt,
              parsedInput(0)("maintenanceCalories").num.toInt,
              parsedInput(0)("targetCalories").num.toInt,
              parsedInput(0)("timeToGoal").num.toInt
            ) :: input
            macroStatList(stats, acc + 1)
          } else {
            input
          }
        }
        Right(macroStatList(List.empty, 0))
      case Left(value) => Left(value)
    }
  }
}
