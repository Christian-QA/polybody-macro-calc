package services

import com.google.inject.Inject
import connectors.PolybodyConnector
import errors.{CustomClientResponse, CustomErrorHandler}
import helpers.ActivityLevel
import models.MacroStat
import play.api.http.Status.{BAD_REQUEST, OK}
import ujson.{Obj, Value}

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
        def macroStatList(
            input: List[MacroStat],
            acc: Int
        ): List[MacroStat] = {
          if (acc < parsedInput.length) {
            println(parsedInput)
            val stats: List[MacroStat] = MacroStat(
              Some(LocalDate.parse(parsedInput(acc)("dateTime").str)),
              ActivityLevel.apply(parsedInput(acc)("activityLevel").str),
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

  ///TODO - Fix issue with requiring .get for Options and .toString with ActivityLevel
  def addMacroStat(
      username: String,
      macroStat: MacroStat
  ): Future[Either[CustomErrorHandler, Int]] = {
    val data: Obj = Obj(
      "activityLevel" -> macroStat.activityLevel.toString,
      "setGoal" -> macroStat.setGoal,
      "proteinPreference" -> macroStat.proteinPreference.get,
      "fatPreference" -> macroStat.fatPreference.get,
      "carbPreference" -> macroStat.carbPreference.get,
      "bodyFat" -> macroStat.bodyFat.get,
      "equationPreference" -> macroStat.equationPreference.get,
      "maintenanceCalories" -> macroStat.maintenanceCalories,
      "targetCalories" -> macroStat.targetCalories,
      "timeToGoal" -> macroStat.timeToGoal
    )
    polybodyConnector.addMacroStats(username, data) match {
      case response if response.statusCode == OK => Future.successful(Right(OK))
      case _ =>
        Future.successful(
          Left(CustomClientResponse("Invalid data submitted", BAD_REQUEST))
        )
    }
  }
}
