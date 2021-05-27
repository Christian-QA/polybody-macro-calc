package models

import org.scalatest.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.{JsResultException, Json}

import java.time.LocalDate

class PreviousWeightSpec extends PlaySpec with GuiceOneAppPerSuite with MockitoSugar {

  val previousWeight = new PreviousWeight(LocalDate.of(2020, 3, 24), 150.5)

  "PreviousWeight" must {

    val json = Json.obj(
      "dateTime" -> "2020-03-24",
      "weight" -> 150.5
    )

    "deserialise valid values" in {

      val result = json.as[PreviousWeight]

      result shouldBe previousWeight

    }

    "deserialise invalid values" in {

      val invalidJson = Json.obj(
        "dateTime" -> "invalidDate",
        "weight" -> "150.5"
      )

      val ex = intercept[JsResultException] {
        invalidJson.as[PreviousWeight]
      }

      ex.getMessage shouldBe "JsResultException(errors:List((/dateTime,List(JsonValidationError(List(That's not a date),List()))), (/weight,List(JsonValidationError(List(error.expected.jsnumber),List())))))"

    }

    "deserialise invalid key" in {

      val invalidJson = Json.obj(
        "invalidKey" -> "2020-03-24",
        "invalidKey" -> "150.5"
      )

      val ex = intercept[JsResultException] {
        invalidJson.as[PreviousWeight]
      }

      ex.getMessage shouldBe "JsResultException(errors:List((/dateTime,List(JsonValidationError(List(error.path.missing),List()))), (/weight,List(JsonValidationError(List(error.path.missing),List())))))"

    }

    "serialise to json" in {

      Json.toJson(previousWeight) shouldBe json

    }

    "serialise/deserialise to the same value" in {

      val result = Json.toJson(previousWeight).as[PreviousWeight]

      result shouldBe previousWeight

    }
  }
}
