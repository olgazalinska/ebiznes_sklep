package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class ShoppingCartController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getShoppingCarts() = Action { implicit request: Request[AnyContent] =>
    Ok("ShoppingCarts controller")
  }

  def getShoppingCart(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("ShoppingCart")
  }

  def createShoppingCart() = Action { implicit request: Request[AnyContent] =>
    Created("ShoppingCart created")
  }

  def updateShoppingCart(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("ShoppingCart updated")
  }

  def deleteShoppingCart(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("ShoppingCart deleted")
  }

}
