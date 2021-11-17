package service

import connectors.PolybodyConnector
import errors.{
  CustomErrorHandler,
  CustomNoContentResponse,
  CustomUpstreamResponse
}
import models.User
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures
import play.api.http.Status.INTERNAL_SERVER_ERROR
import services.UserService
import utils.BaseSpec
import utils.UserDetails.{passUser, passUserUjson, passUsername}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class UserServiceSpec extends BaseSpec with ScalaFutures {

  lazy val polybodyConnector: PolybodyConnector = mock[PolybodyConnector]

  lazy val sut = new UserService(polybodyConnector)

  "UserService" when {
    "getUserDetails is called" must {
      "return a User containing user data if data is retrieved from the connector" in {

        when(polybodyConnector.getUserDetails(passUsername))
          .thenReturn(Future.successful(Right(passUserUjson)))

        val response: Future[Either[CustomErrorHandler, User]] =
          sut.getUserDetails(passUsername)

        val result: Either[CustomErrorHandler, User] =
          Await.result(response, Duration(5, "seconds"))

        result mustBe Right(passUser)
      }
      "return a CustomNoContentResponse if no data is retrieved from the connector" in {
        when(polybodyConnector.getUserDetails(passUsername))
          .thenReturn(Future.successful(Left(CustomNoContentResponse)))

        val response: Future[Either[CustomErrorHandler, User]] =
          sut.getUserDetails(passUsername)

        val result: Either[CustomErrorHandler, User] =
          Await.result(response, Duration(5, "seconds"))

        result mustBe Left(CustomNoContentResponse)
      }
      "return a CustomUpstreamResponse if the connection to the backend fails" in {
        when(polybodyConnector.getUserDetails(passUsername)).thenReturn(
          Future
            .successful(Left(CustomUpstreamResponse("", INTERNAL_SERVER_ERROR)))
        )

        val response: Future[Either[CustomErrorHandler, User]] =
          sut.getUserDetails(passUsername)

        val result: Either[CustomErrorHandler, User] =
          Await.result(response, Duration(5, "seconds"))

        result mustBe Left(CustomUpstreamResponse("", INTERNAL_SERVER_ERROR))
      }
    }
  }

}
