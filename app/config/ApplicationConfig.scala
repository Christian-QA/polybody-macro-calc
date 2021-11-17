package config

import com.google.inject.Inject
import play.api.Configuration

import scala.concurrent.ExecutionContext

class ApplicationConfig @Inject()(val configuration: Configuration)(implicit val ec: ExecutionContext) {

  lazy val baseUrl: String = configuration.getOptional[String]("urls.polybodyBackend").getOrElse("Error")


}