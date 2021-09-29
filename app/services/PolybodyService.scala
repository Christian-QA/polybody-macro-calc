package services

import com.google.inject.Inject
import connectors.PolybodyConnector
import models.{MacroStat, PreviousWeight, User}
import ujson.Value

import java.time.LocalDate
import scala.concurrent.{ExecutionContext, Future}

class PolybodyService @Inject()(polybodyConnector: PolybodyConnector)(implicit ec: ExecutionContext) {

  def getUserDetails(username: String) = {
    val data = polybodyConnector.getUserDetails(username)

    data.map{
      entry =>

//        val previousWeight = entry(0)("previousWeight").arr.

        val y = (entry(0)("previousWeight").arr(0)("dateTime"))

        println(entry(0)("previousWeight").arr(0)("dateTime"))

        //        val macroStat =
//          entry("macroStat").arr.map { embeddedMacroStat =>
//            MacroStat(
//              LocalDate.parse(embeddedMacroStat("dateTime").str),
//              embeddedMacroStat("activityLevel").str,
//              embeddedMacroStat("setGoal").str.toDouble,
//              embeddedMacroStat("proteinPreference").str.toIntOption,
//              embeddedMacroStat("fatPreference").str.toIntOption,
//              embeddedMacroStat("carbPreference").str.toIntOption,
//              embeddedMacroStat("bodyFat").str.toDoubleOption,
//              embeddedMacroStat("equationPreference").strOpt,
//              embeddedMacroStat("maintenanceCalories").str.toInt,
//              embeddedMacroStat("targetCalories").str.toInt,
//              embeddedMacroStat("timeToGoal").str.toInt
//            )
//          }.toList

        User(
          entry(0)("_id").str,
          entry(0)("username").str,
          entry(0)("email").str,
          entry(0)("age").num.toInt,
          entry(0)("gender").str,
          entry(0)("height").num,
          None,
          entry(0)("height").numOpt,
          None
        )

    }

  }

}
