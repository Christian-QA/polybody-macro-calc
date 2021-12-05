package models

import helpers.VeryActive
import play.api.libs.json.{JsResultException, Json}
import utils.BaseSpec

import java.time.LocalDate

class MacroStatSpec extends BaseSpec {

  val macroStat: MacroStat = new MacroStat(
    Some(LocalDate.of(2020, 3, 24)),
    VeryActive,
    Some(160),
    Some(150),
    Some(50),
    Some(200),
    Some(20),
    None,
    2500,
    2000,
    10
  )

  "MacroStat" must {

    val json = Json.obj(
      "dateTime" -> "2020-03-24",
      "activityLevel" -> "VeryActive",
      "setGoal" -> 160,
      "proteinPreference" -> 150,
      "fatPreference" -> 50,
      "carbPreference" -> 200,
      "bodyFat" -> 20,
      "maintenanceCalories" -> 2500,
      "targetCalories" -> 2000,
      "timeToGoal" -> 10
    )

    "deserialise valid values" in {

      val result = json.as[MacroStat]

      result mustBe macroStat
    }

    "deserialise invalid values" in {
      val invalidJson = Json.obj(
        "dateTime" -> "invalidDate",
        "activityLevel" -> "VeryActive",
        "setGoal" -> 160,
        "proteinPreference" -> 150,
        "fatPreference" -> 50,
        "carbPreference" -> 200,
        "bodyFat" -> 20,
        "maintenanceCalories" -> 2500,
        "targetCalories" -> 2000,
        "timeToGoal" -> 10
      )

      val ex = intercept[JsResultException] {
        invalidJson.as[MacroStat]
      }

      ex.getMessage mustBe "JsResultException(errors:List((/dateTime,List(JsonValidationError(List(error.expected.date.isoformat),ArraySeq(ParseCaseSensitive(false)(Value(Year,4,10,EXCEEDS_PAD)'-'Value(MonthOfYear,2)'-'Value(DayOfMonth,2))[Offset(+HH:MM:ss,'Z')]))))))"
    }

    "deserialise invalid key" in {

      val invalidJson = Json.obj(
        "invalidKey" -> "2020-03-24",
        "invalidKey" -> "VeryActive",
        "invalidKey" -> 160,
        "invalidKey" -> 150,
        "invalidKey" -> 50,
        "invalidKey" -> 200,
        "invalidKey" -> 20,
        "invalidKey" -> None,
        "invalidKey" -> 2500,
        "invalidKey" -> 2000,
        "invalidKey" -> 10
      )

      val ex = intercept[JsResultException] {
        invalidJson.as[MacroStat]
      }

      ex.getMessage mustBe "JsResultException(errors:List((/activityLevel,List(JsonValidationError(List(error.path.missing),List()))), (/maintenanceCalories,List(JsonValidationError(List(error.path.missing),List()))), (/timeToGoal,List(JsonValidationError(List(error.path.missing),List()))), (/targetCalories,List(JsonValidationError(List(error.path.missing),List())))))"
    }

    "serialise to json" in {

      Json.toJson(macroStat) mustBe json
    }

    "serialise/deserialise to the same value" in {

      val result = Json.toJson(macroStat).as[MacroStat]

      result mustBe macroStat
    }
  }
}
