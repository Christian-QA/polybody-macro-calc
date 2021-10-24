package service

import connectors.PolybodyConnector
import models.User
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures
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
        "return a Future[User] containing user data if the user exists" in {

          when(polybodyConnector.getUserDetails(passUsername)).thenReturn(Future.successful(passUserUjson))

          val response = sut.getUserDetails(passUsername)

          val result = Await.result(response, Duration(5, "seconds"))

          result mustBe passUser

        }
      }
    }

}
