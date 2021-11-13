package services

import com.google.inject.Inject
import connectors.PolybodyConnector
import errors.CustomErrorHandler
import models.PreviousWeight

import java.time.LocalDate
import scala.concurrent.{ExecutionContext, Future}

class PreviousWeightService @Inject()(polybodyConnector: PolybodyConnector)(implicit ec: ExecutionContext) {

  def getPreviousWeights(username: String): Future[Either[CustomErrorHandler, PreviousWeight]] = {
    val data = polybodyConnector.getPreviousWeights(username)

    data.map {
      case Right(value) =>
        Right(
          PreviousWeight(
            LocalDate.parse(value(0)(0)("dateTime").str),
            value(0)(0)("weight").num
          )
        )
      case Left(value) => Left(value)
    }
  }
}
