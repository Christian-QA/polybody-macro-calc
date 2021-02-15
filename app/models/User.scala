package models

import play.api.libs.json.Json

case class User(
                 forename: String,
                 surname: String,
                 email: String,
                 password: String,
                 age: Int,
                 gender: String,
                 height: Double,
                 previousWeight: List[PreviousWeight],
                 targetWeight: Double,
                 macroStat: List[MacroStat]
               )

object User {

  implicit val formats = Json.format[User]

}

