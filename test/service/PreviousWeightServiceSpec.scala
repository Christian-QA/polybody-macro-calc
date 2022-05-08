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
import models.{PreviousWeight, User}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures
import play.api.http.Status.{
  BAD_GATEWAY,
  BAD_REQUEST,
  INTERNAL_SERVER_ERROR,
  SERVICE_UNAVAILABLE
}
import requests.Response
import services.PreviousWeightService
import utils.BaseSpec
import utils.UserDetails.{
  passPreviousWeightUjson,
  passPreviousWeights,
  passUsername
}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class PreviousWeightServiceSpec extends BaseSpec with ScalaFutures {

  lazy val polybodyConnector: PolybodyConnector = mock[PolybodyConnector]

  lazy val sut = new PreviousWeightService(polybodyConnector)

  "PreviousWeightService" when {
    "getPreviousWeights is called" must {
      "return PreviousWeights containing user data if data is retrieved from the connector" in {

        when(polybodyConnector.getPreviousWeights(any()))
          .thenReturn(Future.successful(Right(passPreviousWeightUjson)))

        val response: Future[Either[CustomErrorHandler, List[PreviousWeight]]] =
          sut.getPreviousWeights(passUsername)

        val result: Either[CustomErrorHandler, List[PreviousWeight]] =
          Await.result(response, Duration(5, "seconds"))

        result mustBe Right(passPreviousWeights)
      }

      List(
        CustomNoContentResponse,
        CustomClientResponse("", BAD_REQUEST),
        CustomUpstreamResponse("", INTERNAL_SERVER_ERROR),
        CustomBackendDownResponse,
        CustomTimeoutResponse
      ).foreach { error =>
        s"return $error if returned from the connector" in {
          when(polybodyConnector.getPreviousWeights(any()))
            .thenReturn(Future.successful(Left(error)))

          val response: Either[CustomErrorHandler, List[PreviousWeight]] =
            sut.getPreviousWeights(passUsername).futureValue

          response mustBe Left(error)
        }
      }
    }

//    "addPreviousWeight is called" must {
//      "return PreviousWeights containing user data if data is retrieved from the connector" in {
//
//        Response()
//
//        when(polybodyConnector.addPreviousWeights(any(), any()))
//          .thenReturn(Future.successful(Right(passPreviousWeightUjson)))
//
//        val response: Future[Either[CustomErrorHandler, List[PreviousWeight]]] =
//          sut.getPreviousWeights(passUsername)
//
//        val result: Either[CustomErrorHandler, List[PreviousWeight]] =
//          Await.result(response, Duration(5, "seconds"))
//
//        result mustBe Right(passPreviousWeights)
//      }
//
//      List(
//        CustomNoContentResponse,
//        CustomClientResponse("", BAD_REQUEST),
//        CustomUpstreamResponse("", INTERNAL_SERVER_ERROR),
//        CustomBackendDownResponse,
//        CustomTimeoutResponse
//      ).foreach { error =>
//        s"return $error if returned from the connector" in {
//          when(polybodyConnector.getPreviousWeights(passUsername))
//            .thenReturn(Future.successful(Left(error)))
//
//          val response: Either[CustomErrorHandler, List[PreviousWeight]] =
//            sut.getPreviousWeights(passUsername).futureValue
//
//          response mustBe Left(error)
//        }
//      }
//    }
  }
}
