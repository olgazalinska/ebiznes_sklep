package models

import play.api.libs.json._

case class ClientCard(id: Long, user: Long, description: String )

object ClientCard {
  implicit val clientcardFormat = Json.format[ClientCard]
}