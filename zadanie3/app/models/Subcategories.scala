package models

import play.api.libs.json._

case class Subcategories(id: Long,name:String, description:String)

object Subcategories {
  implicit val subcategoriesFormat = Json.format[Subcategories]
}