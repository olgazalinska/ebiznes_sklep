package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class OrderController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
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
