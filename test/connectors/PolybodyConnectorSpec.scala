package connectors

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.{aResponse, urlEqualTo}
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.Application
import play.api.http.Status.OK
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Injecting
import utils.UserDetails.{passUserUjson, passUsername}
import utils.WireMockHelper

class PolybodyConnectorSpec
    extends AnyWordSpec
      with ScalaFutures
      with IntegrationPatience
      with Matchers
      with Injecting
      with WireMockHelper {

  override lazy val app: Application =
    new GuiceApplicationBuilder()
      .configure(
        "microservice.polybody-backend.port" -> server.port(),
        "microservice.polybody-backend.host" -> "127.0.0.1"
      )
      .build()

  lazy val polybodyConnector: PolybodyConnector = inject[PolybodyConnector]

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
