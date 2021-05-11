package models

import play.api.libs.json._

case class Transaction(id: Long, user: Long, order: Long, status: Int )

object Transaction {
  implicit val transactionFormat = Json.format[Transaction]
}