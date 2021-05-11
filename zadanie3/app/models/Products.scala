package models

import play.api.libs.json._

case class Products(id: Long,name:String)

object Products {
  implicit val productsFormat = Json.format[Products]
}