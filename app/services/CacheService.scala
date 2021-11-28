package services

import com.google.inject.Inject
import config.ApplicationConfig
import helpers.CacheHelper
import reactivemongo.api.bson.{BSONDocument, document}
import reactivemongo.play.json.compat.json2bson.toDocumentWriter

import scala.concurrent.{ExecutionContext, Future}

class CacheService @Inject() (applicationConfig: ApplicationConfig)(implicit
    ec: ExecutionContext
) {

//  def cacheAdd(selector: CacheHelper, modifier: String): Future[Int] = {
//
//    selector match {
//      case CacheHelper => BSONDocument("sex")
//    }
//
//    applicationConfig.cache
//      .flatMap(_.update.one(selector, modifier, upsert = true))
//      .map(_.n)
//  }

//  def readCache(id: String) = {
//    applicationConfig.cache.flatMap(_.find(document("id" -> id)).cursor())
//  }

}
