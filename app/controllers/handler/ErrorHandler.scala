package controllers.handler

import com.google.inject.Inject
import errors._
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
) {

  def handle(error: CustomErrorHandler): Future[Result] = {
    error match {
      case CustomBackendDownResponse =>
        Future.successful(BadRequest(errorServiceDownView()))
      case CustomClientResponse(message, reportedAs) =>
        Future.successful(BadRequest(errorBadRequestView()))
      case CustomNoContentResponse =>
        Future.successful(Ok(landingPageView())) //TODO - Change to error
      case CustomUpstreamResponse(message, reportedAs) =>
        Future.successful(InternalServerError(errorInternalServerView()))
      case CustomTimeoutResponse =>
        Future.successful(RequestTimeout(errorTimeoutView()))
      case _ =>
        Future.successful(Ok(landingPageView())) //TODO - Change to error
    }

  }

}
