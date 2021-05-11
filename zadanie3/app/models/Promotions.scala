package models

import play.api.libs.json._

case class Promotions(id: Long, product_id: Long, discount: Long )

object Promotions {
  implicit val promotionsFormat = Json.format[Promotions]
}