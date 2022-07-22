package helpers

import play.api.mvc.QueryStringBindable

object ActivityLevel {

  implicit def queryStringBindable(implicit
      intBinder: QueryStringBindable[String]
  ): QueryStringBindable[ActivityLevel] =
    new QueryStringBindable[ActivityLevel] {
      override def bind(
          key: String,
          params: Map[String, Seq[String]]
      ): Option[Either[String, ActivityLevel]] = {
        for {
          activity <- intBinder.bind("activity", params)
        } yield {
          (activity) match {
            case (Right(activity)) => Right(ActivityLevel(activity))
            case _                 => Left("Unable to bind an activity level")
          }
        }
      }
      override def unbind(key: String, activity: ActivityLevel): String = {
        intBinder.unbind("activity", activity.toString)
      }
    }

  def apply(value: String): ActivityLevel = {
    value match {
      case "Sedentary"        => Sedentary
      case "LightlyActive"    => LightlyActive
      case "ModeratelyActive" => ModeratelyActive
      case "VeryActive"       => VeryActive
    }
  }
}

sealed trait ActivityLevel

case object Sedentary extends ActivityLevel
case object LightlyActive extends ActivityLevel
case object ModeratelyActive extends ActivityLevel
case object VeryActive extends ActivityLevel
