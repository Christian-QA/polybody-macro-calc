package models

import play.api.libs.json.{Json, OFormat}

import java.time.LocalDate

case class User(
    _id: String,
    username: String,
    email: String,
    dob: LocalDate,
    sex: String,
    height: Double,
    targetWeight: Option[Double]
)

object User {

  implicit val formats: OFormat[User] = Json.format[User]

}
