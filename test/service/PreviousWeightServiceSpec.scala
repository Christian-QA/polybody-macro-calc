package service

import connectors.PolybodyConnector
import errors.{CustomNoContentResponse, CustomUpstreamResponse}
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures
import play.api.http.Status.INTERNAL_SERVER_ERROR
import services.{PreviousWeightService, UserService}
import utils.BaseSpec
import utils.UserDetails.{passUser, passUserUjson, passUsername}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class PreviousWeightServiceSpec extends BaseSpec with ScalaFutures {

  lazy val polybodyConnector: PolybodyConnector = mock[PolybodyConnector]

  lazy val sut = new PreviousWeightService(polybodyConnector)

    "PreviousWeightService" when {
      "getPreviousWeights is called" must {
        "return PreviousWeights containing user data if data is retrieved from the connector" in {

          when(polybodyConnector.getPreviousWeights(passUsername)).thenReturn(Future.successful(Right(passUserUjson)))

          val response = sut.getPreviousWeights(passUsername)

          val result = Await.result(response, Duration(5, "seconds"))

          result mustBe Right(passUser)
        }
        "return a CustomNoContentResponse if no data is retrieved from the connector" in {
          when(polybodyConnector.getPreviousWeights(passUsername)).thenReturn(Future.successful(Left(CustomNoContentResponse)))

          val response = sut.getPreviousWeights(passUsername)

          val result = Await.result(response, Duration(5, "seconds"))

          result mustBe Left(CustomNoContentResponse)
        }
        "return a CustomUpstreamResponse if the connection to the backend fails" in {
          when(polybodyConnector.getPreviousWeights(passUsername)).thenReturn(Future.successful(Left(CustomUpstreamResponse("", INTERNAL_SERVER_ERROR))))

          val response = sut.getPreviousWeights(passUsername)

          val result = Await.result(response, Duration(5, "seconds"))

          result mustBe Left(CustomUpstreamResponse("", INTERNAL_SERVER_ERROR))
        }
      }
    }

}
