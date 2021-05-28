package models

import play.api.libs.json.{JsResultException, Json}
import utils.BaseSpec

import java.time.LocalDate

class UserSpec  extends BaseSpec {

  val previousWeightList: List[PreviousWeight] = {
    List(
      PreviousWeight(LocalDate.of(2020, 3, 24), 150.5),
      PreviousWeight(LocalDate.of(2020, 2, 24), 144.5)
    )
  }

  val macroStat: MacroStat = new MacroStat(
    LocalDate.of(2020, 3, 24),
    "Very Active",
    160,
    Some(150),
    Some(50),
    Some(200),
    Some(20),
    None,
    2500,
    2000,
    10
  )

  val user: User = new User(
    "testForename",
    "testSurname",
    "testEmail@email.com",
    "testPassword",
    25,
    "male",
    175.5,
    previousWeightList,
    165,
    List(macroStat)
  )

  "User" must {

    val json = Json.obj(
      "forename" -> "testForename",
      "surname" -> "testSurname",
      "email" -> "testEmail@email.com",
      "password" -> "testPassword",
      "age" -> 25,
      "gender" -> "male",
      "height" -> 175.5,
      "previousWeight" -> Json.arr(
        Json.obj(
          "dateTime" -> "2020-03-24",
          "weight" -> 150.5
        ),
        Json.obj(
          "dateTime" -> "2020-02-24",
          "weight" -> 144.5
        )
      ),
      "targetWeight" -> 165,
      "macroStat" -> Json.arr(
        Json.obj(
          "dateTime" -> "2020-03-24",
          "activityLevel" -> "Very Active",
          "setGoal" -> 160,
          "proteinPreference" -> 150,
          "fatPreference" -> 50,
          "carbPreference" -> 200,
          "bodyFat" -> 20,
          "maintenanceCalories" -> 2500,
          "targetCalories" -> 2000,
          "timeToGoal" -> 10
        )
      )
    )

    "deserialise valid values" in {

      val result = json.as[User]

      result mustBe user

    }

    "deserialise invalid values" in {

      val invalidJson = Json.obj(
        "forename" -> 0,
        "surname" -> 0,
        "email" -> 0,
        "password" -> 0,
        "age" -> "25",
        "gender" -> 0,
        "height" -> "175.5",
        "previousWeight" -> Json.arr(),
        "targetWeight" -> "150.5",
        "macroStat" -> Json.arr(),
      )

      val ex = intercept[JsResultException] {
        invalidJson.as[User]
      }

      ex.getMessage mustBe "JsResultException(errors:List((/surname,List(JsonValidationError(List(error.expected.jsstring),List()))), (/height,List(JsonValidationError(List(error.expected.jsnumber),List()))), (/password,List(JsonValidationError(List(error.expected.jsstring),List()))), (/gender,List(JsonValidationError(List(error.expected.jsstring),List()))), (/forename,List(JsonValidationError(List(error.expected.jsstring),List()))), (/targetWeight,List(JsonValidationError(List(error.expected.jsnumber),List()))), (/age,List(JsonValidationError(List(error.expected.jsnumber),List()))), (/email,List(JsonValidationError(List(error.expected.jsstring),List())))))"
    }

    "deserialise invalid key" in {

      val invalidJson = Json.obj(
        "invalidKey" -> "testForename",
        "invalidKey" -> "testSurname",
        "invalidKey" -> "testEmail@email.com",
        "invalidKey" -> "testPassword",
        "invalidKey" -> 25,
        "invalidKey" -> "male",
        "invalidKey" -> 175.5,
        "invalidKey" -> Json.arr(),
        "invalidKey" -> 165,
        "invalidKey" -> Json.arr()
      )

      val ex = intercept[JsResultException] {
        invalidJson.as[User]
      }

      ex.getMessage mustBe "JsResultException(errors:List((/surname,List(JsonValidationError(List(error.path.missing),List()))), (/height,List(JsonValidationError(List(error.path.missing),List()))), (/macroStat,List(JsonValidationError(List(error.path.missing),List()))), (/previousWeight,List(JsonValidationError(List(error.path.missing),List()))), (/password,List(JsonValidationError(List(error.path.missing),List()))), (/gender,List(JsonValidationError(List(error.path.missing),List()))), (/forename,List(JsonValidationError(List(error.path.missing),List()))), (/targetWeight,List(JsonValidationError(List(error.path.missing),List()))), (/age,List(JsonValidationError(List(error.path.missing),List()))), (/email,List(JsonValidationError(List(error.path.missing),List())))))"
    }

    "serialise to json" in {

      Json.toJson(user) mustBe json
    }

    "serialise/deserialise to the same value" in {

      val result = Json.toJson(user).as[User]

      result mustBe user
    }
  }
}
