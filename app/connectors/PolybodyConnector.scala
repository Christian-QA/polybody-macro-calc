package connectors


import com.google.inject.Inject
import config.ApplicationConfig
import errors.{CustomBackendDownResponse, CustomErrorHandler, CustomNoContentResponse, CustomUpstreamResponse}
import play.api.Logging
import play.api.http.Status.{INTERNAL_SERVER_ERROR, NO_CONTENT, OK}
import ujson.Value

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future.never.recover
import scala.concurrent.{ExecutionContext, Future}

class PolybodyConnector @Inject()(val applicationConfig: ApplicationConfig)(implicit ec: ExecutionContext) extends Logging {

  sealed trait DataToBeRetrieved
  private case class UserDataToBeRetrieved(username: String) extends DataToBeRetrieved
  private case class PreviousWeightDataToBeRetrieved(username: String) extends DataToBeRetrieved
  private case class MacroStatDataToBeRetrieved(username: String) extends DataToBeRetrieved

  private def getUserUrl(username: String): String = {
    s"${applicationConfig.baseUrl}/findSpecificUser/$username"
  }

  private def getPreviousWeightsUrl(username: String): String = {
    s"${applicationConfig.baseUrl}/findAllPreviousWeights/$username"
  }

  private def getMacroStatsUrl(username: String): String = {
    s"${applicationConfig.baseUrl}/findAllMacroStats/$username"
  }

    private def errorHandler(dataToBeRetrieved: DataToBeRetrieved): Future[Either[CustomErrorHandler, ArrayBuffer[Value]]] = {
      val selector: String = dataToBeRetrieved match {
        case UserDataToBeRetrieved(value) => getUserUrl(value)
        case PreviousWeightDataToBeRetrieved(value) => getPreviousWeightsUrl(value)
        case MacroStatDataToBeRetrieved(value) => getMacroStatsUrl(value)
      }

      requests.get(selector) match {
        case value if value.statusCode == OK => Future.successful(Right(ujson.read(value.text()).arr))
        case value if value.statusCode == NO_CONTENT => Future.successful(Left(CustomNoContentResponse))
        case _ =>
          logger.error(s"Connection to backend failed with the following error code: $CustomBackendDownResponse")
          Future.successful(Left(CustomBackendDownResponse))

      }
    } recover {
      case e: Exception =>
        logger.error(s"Connection to backend failed with the following error code: $CustomUpstreamResponse")
        Left(CustomUpstreamResponse(s"${e.getMessage} thrown with 500 status", INTERNAL_SERVER_ERROR))
    }



    def getUserDetails(username: String): Future[Either[CustomErrorHandler, ArrayBuffer[Value]]] = {
      errorHandler(UserDataToBeRetrieved(username))
    }

  def getPreviousWeights(username: String): Future[Either[CustomErrorHandler, ArrayBuffer[Value]]] = {
    errorHandler(PreviousWeightDataToBeRetrieved(username))
  }

  def getMacroStats(username: String): Future[Either[CustomErrorHandler, ArrayBuffer[Value]]] = {
    errorHandler(MacroStatDataToBeRetrieved(username))
  }

}