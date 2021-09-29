package models

import play.api.libs.json.{Json, OFormat}

case class User(
                 _id: String,
                 username: String,
                 email: String,
                 age: Int,
                 gender: String,
                 height: Double,
                 previousWeight: Option[List[PreviousWeight]],
                 targetWeight: Option[Double],
                 macroStat: Option[List[MacroStat]]
               )

object User {

  implicit val formats: OFormat[User] = Json.format[User]

}

