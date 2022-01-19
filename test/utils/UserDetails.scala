package utils

import helpers.{Male, Sedentary, VeryActive}
import models.{MacroStat, PreviousWeight, User}
import ujson.Value

import java.time.LocalDate
import scala.collection.mutable.ArrayBuffer

object UserDetails {

  lazy val noUsername = "noUsername"

  lazy val passUsername = "testUser"

  lazy val passUserUjson: ArrayBuffer[Value] = ArrayBuffer(
    ujson.Value(
      """{"_id":"611be0d7e17315ce09335455","username":"testUser","email":"testUser@gmail.com","dob":"1996-10-10","sex":"Male","height":190,"targetWeight":140}"""
    )
  )

  lazy val passUser: User = User(
    "611be0d7e17315ce09335455",
    "testUser",
    "testUser@gmail.com",
    LocalDate.of(1996, 10, 10),
    Male,
    190d,
    Some(140d)
  )

  lazy val passPreviousWeightUjson: ArrayBuffer[Value] = ArrayBuffer(
    ujson.Value(
      """[{"dateTime":"2020-06-09","weight":250},{"dateTime":"2021-06-09","weight":180},{"dateTime":"2021-07-09","weight":170},{"dateTime":"2021-08-09","weight":165},{"dateTime":"2021-10-05","weight":450}]"""
    )
  )

  lazy val passPreviousWeights: List[PreviousWeight] = List(
    PreviousWeight(LocalDate.parse("2021-10-05"), 450d),
    PreviousWeight(LocalDate.parse("2021-08-09"), 165d),
    PreviousWeight(LocalDate.parse("2021-07-09"), 170d),
    PreviousWeight(LocalDate.parse("2021-06-09"), 180d),
    PreviousWeight(LocalDate.parse("2020-06-09"), 250d)
  )

  lazy val passMacroStatsUjson: ArrayBuffer[Value] = ArrayBuffer(
    ujson.Value(
      """[{"dateTime":"2021-08-09","activityLevel":"VeryActive","setGoal":220,"proteinPreference":222,"fatPreference":33,"carbPreference":444,"bodyFat":13,"equationPreference":"Default","maintenanceCalories":2900,"targetCalories":2500,"timeToGoal":90},{"dateTime":"2021-03-09","activityLevel":"Sedentary","setGoal":111,"proteinPreference":150,"fatPreference":50,"carbPreference":300,"bodyFat":13,"equationPreference":"Default","maintenanceCalories":2900,"targetCalories":2500,"timeToGoal":90}]"""
    )
  )

  lazy val passMacroStats: List[MacroStat] = List(
    MacroStat(
      Some(LocalDate.parse("2021-03-09")),
      Sedentary,
      Some(111),
      Some(150),
      Some(50),
      Some(300),
      Some(13.0),
      Some("Default"),
      2900,
      2500,
      90
    ),
    MacroStat(
      Some(LocalDate.parse("2021-08-09")),
      VeryActive,
      Some(220),
      Some(222),
      Some(33),
      Some(444),
      Some(13.0),
      Some("Default"),
      2900,
      2500,
      90
    )
  )
}
