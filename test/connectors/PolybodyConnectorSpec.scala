package connectors

import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{reset, when}
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import ujson.Value.JsonableString
import ujson.{Value, write}
import utils.BaseSpec
import utils.UserDetails.{passUserUjson, passUsername}

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

class PolybodyConnectorSpec extends BaseSpec with ScalaFutures with IntegrationPatience {

  lazy val polybodyConnector: PolybodyConnector = mock[PolybodyConnector]

  override lazy val app: Application = GuiceApplicationBuilder()
    .build()

//  override def beforeEach(): Unit = {
//    reset(polybodyConnector)
//  }

  "PolybodyConnector" when {
    "getUserDetails is called" must {
      "return a Future[ArrayBuffer[Value]] containing user data if the user exists" in {

        println(passUserUjson)

        when(polybodyConnector.getUserDetails(passUsername)).thenReturn(Future.successful(Right(passUserUjson)))

        val response = polybodyConnector.getUserDetails(passUsername)

        val result = Await.result(response, Duration(5, "seconds"))

        result mustBe Right(passUserUjson)

      }
    }
  }


}
