package services

import com.google.inject.Inject
import connectors.PolybodyConnector
import models.{PreviousWeight, User}

import java.time.LocalDate
import scala.concurrent.ExecutionContext

class PreviousWeightService @Inject()(polybodyConnector: PolybodyConnector)(implicit ec: ExecutionContext) {

  def getPreviousWeights(username: String) = {
    val data = polybodyConnector.getPreviousWeights(username)

    data.map{
      entry =>
        PreviousWeight(
          LocalDate.parse(entry(0)("dateTime").str),
          entry(0)("weight").num
        )
    }
  }
}
