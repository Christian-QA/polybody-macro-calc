package connectors

import errors.{
  CustomErrorHandler,
  CustomNoContentResponse,
  CustomUpstreamResponse
}
import org.mockito.Mockito.when
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import play.api.Application
import play.api.http.Status.INTERNAL_SERVER_ERROR
import play.api.inject.guice.GuiceApplicationBuilder
import ujson.Value
import utils.BaseSpec
import utils.UserDetails.{passUserUjson, passUsername}

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class PolybodyConnectorSpec
    extends BaseSpec
    with ScalaFutures
    with IntegrationPatience {

  lazy val polybodyConnector: PolybodyConnector = mock[PolybodyConnector]

  override lazy val app: Application = GuiceApplicationBuilder()
    .build()

  "PolybodyConnector" when {
    "getUserDetails is called" must {
      "return a Future[ArrayBuffer[Value]] containing user data if the user exists" in {

        when(polybodyConnector.getUserDetails(passUsername))
          .thenReturn(Future.successful(Right(passUserUjson)))

        val response: Future[Either[CustomErrorHandler, ArrayBuffer[Value]]] =
          polybodyConnector.getUserDetails(passUsername)

        val result: Either[CustomErrorHandler, ArrayBuffer[Value]] =
          Await.result(response, Duration(5, "seconds"))

        result mustBe Right(passUserUjson)
      }
      "return a UserNoContentResponse if the user doesn't exist" in {

        when(polybodyConnector.getUserDetails(passUsername))
          .thenReturn(Future.successful(Left(CustomNoContentResponse)))

        val response: Future[Either[CustomErrorHandler, ArrayBuffer[Value]]] =
          polybodyConnector.getUserDetails(passUsername)

        val result: Either[CustomErrorHandler, ArrayBuffer[Value]] =
          Await.result(response, Duration(5, "seconds"))

        result mustBe Left(CustomNoContentResponse)
      }
      "return a UserUpstreamResponse if the connection to the backend fails" in {

        when(polybodyConnector.getUserDetails(passUsername)).thenReturn(
          Future
            .successful(Left(CustomUpstreamResponse("", INTERNAL_SERVER_ERROR)))
        )

        val response: Future[Either[CustomErrorHandler, ArrayBuffer[Value]]] =
          polybodyConnector.getUserDetails(passUsername)

        val result: Either[CustomErrorHandler, ArrayBuffer[Value]] =
          Await.result(response, Duration(5, "seconds"))

        result mustBe Left(CustomUpstreamResponse("", INTERNAL_SERVER_ERROR))
      }
    }
  }
}
