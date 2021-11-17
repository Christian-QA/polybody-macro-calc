package controllers

import com.google.inject.Inject
import errors.{CustomErrorHandler, CustomNoContentResponse}
import models.MacroStat
import play.api.Logging
import play.api.mvc.Results.Ok
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.{MacroStatService, PreviousWeightService, UserService}

import scala.concurrent.{ExecutionContext, Future}

///Todo - This class is more to live test the routes than provide purpose in production. This will be deleted after the Views have been made
class DataController @Inject() (
    userService: UserService,
    previousWeightService: PreviousWeightService,
    macroStatService: MacroStatService,
    cc: ControllerComponents
)(implicit val ec: ExecutionContext)
    extends BaseController
    with Logging {
  override protected def controllerComponents: ControllerComponents = cc

  def getUserDetails(username: String): Action[AnyContent] =
    Action.async { implicit request =>
      val result = userService.getUserDetails(username)

      result.map {
        case Right(value)                  => Ok(value.toString)
        case Left(CustomNoContentResponse) => NoContent
        case _                             => InternalServerError
      }
    }

  def getPreviousWeights(username: String): Action[AnyContent] =
    Action.async { implicit request =>
      val result = previousWeightService.getPreviousWeights(username)

      result.map {
        case Right(value)                  => Ok(value.toString)
        case Left(CustomNoContentResponse) => NoContent
        case _                             => InternalServerError
      }
    }

  def addPreviousWeights(username: String): Action[AnyContent] =
    Action.async { implicit request =>
      val weight = 500d

      val result: Future[Either[CustomErrorHandler, Int]] =
        previousWeightService.addPreviousWeight(username, weight)

      result.map {
        case Right(value)                  => Ok(value.toString)
        case Left(CustomNoContentResponse) => NoContent
        case _                             => InternalServerError
      }
    }

  def getMacroStats(username: String): Action[AnyContent] =
    Action.async { implicit request =>
      val result = macroStatService.getMacroStats(username)

      result.map {
        case Right(value)                  => Ok(value.toString)
        case Left(CustomNoContentResponse) => NoContent
        case _                             => InternalServerError
      }
    }

  def addMacroStat(username: String): Action[AnyContent] =
    Action.async { implicit request =>
      val macroStat: MacroStat = MacroStat(
        dateTime = None,
        activityLevel = "Sedentary",
        setGoal = 100d,
        proteinPreference = Some(50),
        fatPreference = Some(150),
        carbPreference = Some(1500),
        bodyFat = Some(25.0),
        equationPreference = Some("Default"),
        maintenanceCalories = 4000,
        targetCalories = 4000,
        timeToGoal = 500
      )

      val result: Future[Either[CustomErrorHandler, Int]] =
        macroStatService.addMacroStat(username, macroStat)

      result.map {
        case Right(value) =>
          println("1" * 100)
          Ok(value.toString)
        case Left(CustomNoContentResponse) =>
          println("2" * 100)

          NoContent
        case _ =>
          println("3" * 100)
          InternalServerError
      }
    }
}
