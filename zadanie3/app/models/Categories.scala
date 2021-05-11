package models

import play.api.libs.json._

case class Categories(id: Long,name:String, description:String)

object Categories {
  implicit val categoriesFormat = Json.format[Categories]
}