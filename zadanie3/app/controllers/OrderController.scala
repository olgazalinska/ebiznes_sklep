package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class OrderController @Inject()(cc: ControllerComponents) extends AbstractController(cc){
  def getOrders() = Action { implicit request: Request[AnyContent] =>
    Ok("Orders controller")
  }

  def getOrder(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Order")
  }

  def createOrder() = Action { implicit request: Request[AnyContent] =>
    Created("Order created")
  }

  def updateOrder(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Order updated")
  }

  def deleteOrder(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Order deleted")
  }

}
