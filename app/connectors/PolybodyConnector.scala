package connectors

import models.User
import play.api.Configuration
import play.libs.Json

import scala.concurrent.{ExecutionContext, Future}

//import akka.http.scaladsl.model.{HttpEntity, HttpHeader, HttpMethods, HttpProtocols, HttpRequest}

import akka.actor.ActorSystem
//import akka.http.scaladsl.Http
//import akka.http.scaladsl.model._
import scalaj.http._


class PolybodyConnector (val config: Configuration)(ec: ExecutionContext) {

  val baseUrl: String = config.getOptional[String]("urls.polybodyBackend").getOrElse("Error")
  implicit val system: ActorSystem = ActorSystem()

  def getUserUrl(username: String): String ={
    s"$baseUrl/findSpecificUser/$username"
  }
//  def get(uri: String) = new HttpRequest(method = HttpMethods.GET,
//                                         uri = uri,
//                                         headers = Nil,
//                                         entity = HttpEntity.Empty,
//                                         protocol = HttpProtocols.`HTTP/1.1`)

  //    val response = HttpRequest.apply(HttpRequest(uri = getUserUrl(username)))

  //TODO - help plz
//  def getUser(username: String): Future[User] = {
//    val response: HttpResponse[Json] = Http(getUserUrl(username))
//    response.body
//  }

}
