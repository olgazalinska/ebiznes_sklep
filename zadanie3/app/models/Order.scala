package models

import play.api.libs.json._

case class Order(id: Long, user: Long )

object Order {
  implicit val orderFormat = Json.format[Order]
}