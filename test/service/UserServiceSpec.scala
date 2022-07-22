package service

import connectors.PolybodyConnector
import errors._
import models.User
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures
import play.api.http.Status.{BAD_REQUEST, INTERNAL_SERVER_ERROR}
import services.UserService
import utils.BaseSpec
import utils.UserDetails.{passUser, passUserUjson, passUsername}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class UserServiceSpec extends BaseSpec {

  lazy val polybodyConnector: PolybodyConnector = mock[PolybodyConnector]

  lazy val sut = new UserService(polybodyConnector)

  "UserService" when {
    "getUserDetails is called" must {
      "return a User containing user data if data is retrieved from the connector" in {

        when(polybodyConnector.getUserDetails(any()))
          .thenReturn(Future.successful(Right(passUserUjson)))

        val response: Either[CustomErrorHandler, User] =
          sut.getUserDetails(passUsername).futureValue

        response mustBe Right(passUser)
      }

      List(
        CustomNoContentResponse,
        CustomClientResponse("", BAD_REQUEST),
        CustomUpstreamResponse("", INTERNAL_SERVER_ERROR),
        CustomBackendDownResponse,
        CustomTimeoutResponse
      ).foreach { error =>
        s"return $error if returned from the connector" in {
          when(polybodyConnector.getUserDetails(any()))
            .thenReturn(Future.successful(Left(error)))

          val response: Either[CustomErrorHandler, User] =
            sut.getUserDetails(passUsername).futureValue

          response mustBe Left(error)
        }
      }
    }
  }

}
