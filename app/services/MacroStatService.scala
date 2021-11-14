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

class MacroStatService @Inject() (polybodyConnector: PolybodyConnector)(implicit
    ec: ExecutionContext
) {

  def getMacroStats(
      username: String
  ): Future[Either[CustomErrorHandler, List[MacroStat]]] = {
    val data: Future[Either[CustomErrorHandler, ArrayBuffer[Value]]] =
      polybodyConnector.getMacroStats(username)

    data.map {
      case Right(value) =>
        val parsedInput: ArrayBuffer[Value] = value(0).arr

        @tailrec
        def macroStatList(input: List[MacroStat], acc: Int): List[MacroStat] = {
          if (acc < parsedInput.length) {
            println(parsedInput)
            val stats: List[MacroStat] = MacroStat(
              LocalDate.parse(parsedInput(acc)("dateTime").str),
              parsedInput(acc)("activityLevel").str,
              parsedInput(acc)("setGoal").num,
              Some(parsedInput(acc)("proteinPreference").num.toInt),
              Some(parsedInput(acc)("fatPreference").num.toInt),
              Some(parsedInput(acc)("carbPreference").num.toInt),
              Some(parsedInput(acc)("bodyFat").num.toInt),
              parsedInput(acc)("equationPreference").strOpt,
              parsedInput(acc)("maintenanceCalories").num.toInt,
              parsedInput(acc)("targetCalories").num.toInt,
              parsedInput(acc)("timeToGoal").num.toInt
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
