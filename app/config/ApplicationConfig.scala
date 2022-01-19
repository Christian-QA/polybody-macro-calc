package config

import com.google.inject.Inject
import org.mongodb.scala.{
  MongoClient,
  MongoCollection,
  MongoDatabase,
  connection
}
import play.api.Configuration
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.{AsyncDriver, DB, MongoConnection}

import scala.concurrent.{ExecutionContext, Future}

class ApplicationConfig @Inject() (val configuration: Configuration)(implicit
    val ec: ExecutionContext
) {

  lazy val baseUrl: String =
    configuration.getOptional[String]("urls.polybodyBackend").getOrElse("Error")

}
