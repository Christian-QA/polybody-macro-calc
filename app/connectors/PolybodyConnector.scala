package connectors


import com.google.inject.Inject
import config.ApplicationConfig
import models.User

import scala.concurrent.{ExecutionContext, Future}

class PolybodyConnector @Inject()(val applicationConfig: ApplicationConfig)(implicit ec: ExecutionContext) {

    private def getUserUrl(username: String): String = {
      s"${applicationConfig.baseUrl}/findSpecificUser/$username"
    }

    def getUserDetails(username: String) = {

      val response = requests.get(getUserUrl(username))

      Future.successful(ujson.read(response.text()).arr)
   }
}