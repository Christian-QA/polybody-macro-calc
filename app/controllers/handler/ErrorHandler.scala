package controllers.handler

import com.google.inject.Inject
import errors._
import play.api.Logging
import play.api.mvc.Result
import play.api.mvc.Results.{
  BadRequest,
  InternalServerError,
  Ok,
  RequestTimeout
}
import views.html.{
  ErrorBadRequestView,
  ErrorInternalServerView,
  ErrorServiceDownView,
  ErrorTimeoutView,
  LandingPageView
}

import scala.concurrent.Future

class ErrorHandler @Inject() (
    landingPageView: LandingPageView,
    errorBadRequestView: ErrorBadRequestView,
    errorInternalServerView: ErrorInternalServerView,
    errorServiceDownView: ErrorServiceDownView,
    errorTimeoutView: ErrorTimeoutView
) extends Logging {

  def handle(
      error: CustomErrorHandler,
      controllerName: String
  ): Future[Result] = {
    error match {
      case CustomBackendDownResponse =>
        logger.warn(
          s"Controller with the name '$controllerName' failed due to error: $error"
        )
        Future.successful(BadRequest(errorServiceDownView()))
      case CustomClientResponse(message, reportedAs) =>
        logger.error(
          s"Controller with the name '$controllerName' failed due to error: $error"
        )
        Future.successful(BadRequest(errorBadRequestView()))
      case CustomNoContentResponse =>
        logger.warn(
          s"Controller with the name '$controllerName' failed due to error: $error"
        )
        Future.successful(Ok(landingPageView())) //TODO - Change to error
      case CustomUpstreamResponse(message, reportedAs) =>
        logger.error(
          s"Controller with the name '$controllerName' failed due to error: $error"
        )
        Future.successful(InternalServerError(errorInternalServerView()))
      case CustomTimeoutResponse =>
        logger.warn(
          s"Controller with the name '$controllerName' failed due to error: $error"
        )
        Future.successful(RequestTimeout(errorTimeoutView()))
      case _ =>
        Future.successful(Ok(landingPageView())) //TODO - Change to error
    }

  }

}
