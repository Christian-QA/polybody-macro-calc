package service

import connectors.PolybodyConnector
import errors.{
  CustomBackendDownResponse,
  CustomClientResponse,
  CustomErrorHandler,
  CustomNoContentResponse,
  CustomTimeoutResponse,
  CustomUpstreamResponse
}
import models.{MacroStat, PreviousWeight}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures
import play.api.http.Status.{BAD_REQUEST, INTERNAL_SERVER_ERROR}
import services.MacroStatService
import utils.BaseSpec
import utils.UserDetails.{passMacroStats, passMacroStatsUjson, passUsername}

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

      List(
        CustomNoContentResponse,
        CustomClientResponse("", BAD_REQUEST),
        CustomUpstreamResponse("", INTERNAL_SERVER_ERROR),
        CustomBackendDownResponse,
        CustomTimeoutResponse
      ).foreach { error =>
        s"return $error if returned from the connector" in {
          when(polybodyConnector.getMacroStats(any()))
            .thenReturn(Future.successful(Left(error)))

          val response: Either[CustomErrorHandler, List[MacroStat]] =
            sut.getMacroStats(passUsername).futureValue

          response mustBe Left(error)
        }
      }
    }
  }

  // TODO - Need addMacroStat test following Either refactor
}
