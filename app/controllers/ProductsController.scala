package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class ProductsController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getProducts() = Action { implicit request: Request[AnyContent] =>
    Ok("Products controller")
  }

  def getProduct(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Product")
  }

  def createProduct() = Action { implicit request: Request[AnyContent] =>
    Created("Product created")
  }

  def updateProduct(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Product updated")
  }

  def deleteProduct(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Product deleted")
  }

}
