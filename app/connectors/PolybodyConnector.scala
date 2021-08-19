package connectors

import play.api.Configuration

class PolybodyConnector (val config: Configuration) {

  val baseUrl: String = config.get[String]("urls.polybodyBackend")

  def getSpecificUserUrl(username: String): String ={
    s"$baseUrl/findSpecificUser/$username"
  }

}
