package connectors


import com.google.inject.Inject
import config.ApplicationConfig
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

    def getUserDetails(username: String): Future[ArrayBuffer[Value]] = {

      val response = requests.get(getUserUrl(username))

      println(ujson.read(response.text()).arr)

      Future.successful(ujson.read(response.text()).arr)
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