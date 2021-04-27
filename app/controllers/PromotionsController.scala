package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class PromotionsController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getPromotions() = Action { implicit request: Request[AnyContent] =>
    Ok("Promotions controller")
  }

  def getPromotion(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Promotion")
  }

  def createPromotion() = Action { implicit request: Request[AnyContent] =>
    Created("Promotion created")
  }

  def updatePromotion(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Promotion updated")
  }

  def deletePromotion(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Promotion deleted")
  }

}
