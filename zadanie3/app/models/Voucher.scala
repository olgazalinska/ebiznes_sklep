package models

import play.api.libs.json._

case class Voucher(id: Long, user: Long )

object Voucher {
  implicit val voucherFormat = Json.format[Voucher]
}