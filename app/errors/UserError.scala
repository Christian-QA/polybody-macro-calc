package errors

trait CustomErrorHandler

case object CustomNoContentResponse extends CustomErrorHandler
case class CustomClientResponse(message: String, reportedAs: Int)
    extends CustomErrorHandler
case class CustomUpstreamResponse(message: String, reportedAs: Int)
    extends CustomErrorHandler
case object CustomBackendDownResponse extends CustomErrorHandler
