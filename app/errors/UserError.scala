package errors

trait UserErrorHandler

case object UserNoContentResponse extends UserErrorHandler
//case object UserUpstreamResponse extends UserErrorHandler