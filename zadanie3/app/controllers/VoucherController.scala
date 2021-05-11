package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class VoucherController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def getVouchers() = Action { implicit request: Request[AnyContent] =>
    Ok("Vouchers controller")
  }

  def getVoucher(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Voucher")
  }

  def createVoucher() = Action { implicit request: Request[AnyContent] =>
    Created("Voucher created")
  }

  def updateVoucher(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Voucher updated")
  }

  def deleteVoucher(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Voucher deleted")
  }

}
