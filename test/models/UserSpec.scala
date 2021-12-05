package models

import helpers.{Male, VeryActive}
import play.api.libs.json.{JsObject, JsResultException, Json}
import utils.BaseSpec

import java.time.LocalDate

class UserSpec extends BaseSpec {

  val previousWeightList: List[PreviousWeight] = {
    List(
      PreviousWeight(LocalDate.of(2020, 3, 24), 150.5),
      PreviousWeight(LocalDate.of(2020, 2, 24), 144.5)
    )
  }

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

  val user: User = new User(
    "testId",
    "testUsername",
    "testEmail@email.com",
    LocalDate.of(1996, 10, 10),
    Male,
    175.5,
    Some(165)
  )

  "User" must {

    val json: JsObject = Json.obj(
      "_id" -> "testId",
      "username" -> "testUsername",
      "email" -> "testEmail@email.com",
      "dob" -> "1996-10-10",
      "sex" -> "Male",
      "height" -> 175.5,
      "targetWeight" -> 165
    )

    "deserialise valid values" in {

      val result: User = json.as[User]

      result mustBe user

    }

    "deserialise invalid values" in {

      val invalidJson: JsObject = Json.obj(
        "username" -> 0,
        "email" -> 0,
        "dob" -> "1996-10-10",
        "sex" -> 0,
        "height" -> "175.5",
        "targetWeight" -> "150.5"
      )

      val ex: JsResultException = intercept[JsResultException] {
        invalidJson.as[User]
      }

      ex.getMessage mustBe "JsResultException(errors:List((/height,List(JsonValidationError(List(error.expected.jsnumber),List()))), (/username,List(JsonValidationError(List(error.expected.jsstring),List()))), (/_id,List(JsonValidationError(List(error.path.missing),List()))), (/targetWeight,List(JsonValidationError(List(error.expected.jsnumber),List()))), (/email,List(JsonValidationError(List(error.expected.jsstring),List()))), (/sex,List(JsonValidationError(List(That's not a sex),List())))))"
    }

    "deserialise invalid key" in {

      val invalidJson = Json.obj(
        "invalidKey" -> "testUsername",
        "invalidKey" -> "testEmail@email.com",
        "invalidKey" -> "1996-10-10",
        "invalidKey" -> "Male",
        "invalidKey" -> 175.5,
        "invalidKey" -> 165
      )

      val ex = intercept[JsResultException] {
        invalidJson.as[User]
      }

      ex.getMessage mustBe "JsResultException(errors:List((/dob,List(JsonValidationError(List(error.path.missing),List()))), (/height,List(JsonValidationError(List(error.path.missing),List()))), (/username,List(JsonValidationError(List(error.path.missing),List()))), (/_id,List(JsonValidationError(List(error.path.missing),List()))), (/email,List(JsonValidationError(List(error.path.missing),List()))), (/sex,List(JsonValidationError(List(error.path.missing),List())))))"
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
