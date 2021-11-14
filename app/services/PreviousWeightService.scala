package services

import com.google.inject.Inject
import connectors.PolybodyConnector
import errors.CustomErrorHandler
import models.PreviousWeight
import ujson.Value

import java.time.LocalDate
import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{ExecutionContext, Future}

class PreviousWeightService @Inject() (polybodyConnector: PolybodyConnector)(
    implicit ec: ExecutionContext
) {

  def getPreviousWeights(
      username: String
  ): Future[Either[CustomErrorHandler, List[PreviousWeight]]] = {
    val data: Future[Either[CustomErrorHandler, ArrayBuffer[Value]]] =
      polybodyConnector.getPreviousWeights(username)

    data.map {
      case Right(value) =>
        val parsedInput: ArrayBuffer[Value] = value(0).arr

        @tailrec
        def previousWeightList(
            input: List[PreviousWeight],
            acc: Int
        ): List[PreviousWeight] = {
          if (acc < parsedInput.length) {
            val weight = PreviousWeight(
              LocalDate.parse(parsedInput(acc)("dateTime").str),
              parsedInput(acc)("weight").num
            ) :: input
            previousWeightList(weight, acc + 1)
          } else {
            input
          }
        }
        Right(previousWeightList(List.empty, 0))
      case Left(value) => Left(value)
    }
  }
}
