package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class ShoppingCartController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
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
