package connectors


import com.google.inject.Inject
import config.ApplicationConfig
import errors.{UserErrorHandler, UserNoContentResponse}
import play.api.http.Status.OK
import ujson.Value

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{ExecutionContext, Future}

class PolybodyConnector @Inject()(val applicationConfig: ApplicationConfig)(implicit ec: ExecutionContext) {

    private def getUserUrl(username: String): String = {
      s"${applicationConfig.baseUrl}/findSpecificUser/$username"
    }

  private def getPreviousWeightsUrl(username: String): String = {
    s"${applicationConfig.baseUrl}/findAllPreviousWeights/$username"
  }

  private def getMacroStatsUrl(username: String): String = {
    s"${applicationConfig.baseUrl}/findAllMacroStats/$username"
  }

    def getUserDetails(username: String): Future[Either[UserErrorHandler, ArrayBuffer[Value]]] = {

      requests.get(getUserUrl(username)) match {
        case value if value.statusCode == OK => Future.successful(Right(ujson.read(value.text()).arr))
        case _ => Future.successful(Left(UserNoContentResponse))
      }
   }

  def getPreviousWeights(username: String): Future[ArrayBuffer[Value]] = {

    val response = requests.get(getUserUrl(username))

    Future.successful(ujson.read(response.text()).arr)
  }

  def getMacroStats(username: String): Future[ArrayBuffer[Value]] = {

    val response = requests.get(getUserUrl(username))

    Future.successful(ujson.read(response.text()).arr)
  }

}