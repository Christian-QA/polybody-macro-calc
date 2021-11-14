package utils

import models.{PreviousWeight, User}
import ujson.Value

import java.time.LocalDate
import scala.collection.mutable.ArrayBuffer

object UserDetails {

  lazy val noUsername = "noUsername"

  lazy val passUsername = "testUser"

  lazy val passUserUjson: ArrayBuffer[Value] = ArrayBuffer(ujson.Value("""{"_id":"611be0d7e17315ce09335455","username":"testUser","email":"testUser@gmail.com","age":25,"gender":"male","height":190,"targetWeight":140}"""))


  lazy val passUser: User = User("611be0d7e17315ce09335455", "testUser", "testUser@gmail.com", 25,"male", 190d, Some(140d))

  lazy val passPreviousWeight: PreviousWeight = PreviousWeight(LocalDate.parse("2021-09-01"), 150d)


}