package controllers.handler

import com.google.inject.Inject
import controllers.routes
import errors._
import play.api.Logging
import play.api.i18n.MessagesApi
import play.api.mvc.Result
import play.api.mvc.Results.{
  BadRequest,
  InternalServerError,
  Ok,
  Redirect,
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
  )(implicit messages: MessagesApi): Future[Result] = {
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
        Future.successful(Redirect(routes.LandingPageController.index()))
      //TODO - Change to error
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
        Future.successful(Redirect(routes.LandingPageController.index()))
      //TODO - Change to error
    }

  }

}
