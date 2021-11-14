package service

import connectors.PolybodyConnector
import errors.{
  CustomErrorHandler,
  CustomNoContentResponse,
  CustomUpstreamResponse
}
import models.MacroStat
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures
import play.api.http.Status.INTERNAL_SERVER_ERROR
import services.{MacroStatService, PreviousWeightService}
import utils.BaseSpec
import utils.UserDetails.{
  passMacroStats,
  passMacroStatsUjson,
  passPreviousWeightUjson,
  passPreviousWeights,
  passUsername
}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class MacroStatServiceSpec extends BaseSpec with ScalaFutures {

  lazy val polybodyConnector: PolybodyConnector = mock[PolybodyConnector]

  lazy val sut = new MacroStatService(polybodyConnector)

  "MacroStatService" when {
    "getMacroStats is called" must {
      "return MacroStats containing user data if data is retrieved from the connector" in {

        when(polybodyConnector.getMacroStats(passUsername))
          .thenReturn(Future.successful(Right(passMacroStatsUjson)))

        val response: Future[Either[CustomErrorHandler, List[MacroStat]]] =
          sut.getMacroStats(passUsername)

        val result: Either[CustomErrorHandler, List[MacroStat]] =
          Await.result(response, Duration(5, "seconds"))

        result mustBe Right(passMacroStats)
      }
      "return a CustomNoContentResponse if no data is retrieved from the connector" in {
        when(polybodyConnector.getMacroStats(passUsername))
          .thenReturn(Future.successful(Left(CustomNoContentResponse)))

        val response: Future[Either[CustomErrorHandler, List[MacroStat]]] =
          sut.getMacroStats(passUsername)

        val result: Either[CustomErrorHandler, List[MacroStat]] =
          Await.result(response, Duration(5, "seconds"))

        result mustBe Left(CustomNoContentResponse)
      }
      "return a CustomUpstreamResponse if the connection to the backend fails" in {
        when(polybodyConnector.getMacroStats(passUsername)).thenReturn(
          Future
            .successful(Left(CustomUpstreamResponse("", INTERNAL_SERVER_ERROR)))
        )

        val response: Future[Either[CustomErrorHandler, List[MacroStat]]] =
          sut.getMacroStats(passUsername)

        val result: Either[CustomErrorHandler, List[MacroStat]] =
          Await.result(response, Duration(5, "seconds"))

        result mustBe Left(CustomUpstreamResponse("", INTERNAL_SERVER_ERROR))
      }
    }
  }

}
