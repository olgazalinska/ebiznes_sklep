package models

import play.api.libs.json._

case class ShoppingCart(id: Long, user: Long, order: Long, price: Double )

object ShoppingCart {
  implicit val shoppingcartFormat = Json.format[ShoppingCart]
}