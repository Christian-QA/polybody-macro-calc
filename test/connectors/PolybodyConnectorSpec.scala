package connectors

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.{aResponse, urlEqualTo}
import errors.{
  CustomErrorHandler,
  CustomNoContentResponse,
  CustomUpstreamResponse
}
import org.mockito.Mockito.when
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import play.api.Application
import play.api.http.Status.{INTERNAL_SERVER_ERROR, OK}
import play.api.inject.guice.GuiceApplicationBuilder
import ujson.Value
import utils.{BaseSpec, WireMockHelper}
import utils.UserDetails.{passUserUjson, passUsername}

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class PolybodyConnectorSpec
    extends BaseSpec
    with ScalaFutures
    with IntegrationPatience
    with WireMockHelper {

  lazy val polybodyConnector: PolybodyConnector = mock[PolybodyConnector]

  override lazy val app: Application = GuiceApplicationBuilder()
    .build()

  "PolybodyConnector" when {
    "getUserDetails is called" must {
      val url = s"/findSpecificUser/$passUsername"

      "return a Future[ArrayBuffer[Value]] containing user data if the user exists" in {

        server.stubFor(
          WireMock
            .get(urlEqualTo(url))
            .willReturn(
              aResponse()
                .withStatus(OK)
                .withBody(passUserUjson.toString)
            )
        )

//        when(polybodyConnector.getUserDetails(passUsername))
//          .thenReturn(Future.successful(Right(passUserUjson)))

        val response = polybodyConnector.getUserDetails(passUsername)

        response.futureValue mustBe Right(passUserUjson)
      }

//      "return a UserNoContentResponse if the user doesn't exist" in {
//
//
//        result mustBe Left(CustomNoContentResponse)
//      }
//      "return a UserUpstreamResponse if the connection to the backend fails" in {
//
//
//        result mustBe Left(CustomUpstreamResponse("", INTERNAL_SERVER_ERROR))
//      }
    }
  }
}
