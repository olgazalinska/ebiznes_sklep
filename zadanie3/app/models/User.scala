package models

import play.api.libs.json.Json

case class User(id: Long, login: String, email: String, password: String)


object User {
  implicit val userFormat = Json.format[User]
}