package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class ClientCardController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getClientCards() = Action { implicit request: Request[AnyContent] =>
    Ok("ClientCards controller")
  }

  def getClientCard(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("ClientCard")
  }

  def createClientCard() = Action { implicit request: Request[AnyContent] =>
    Created("ClientCard created")
  }

  def updateClientCard(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("ClientCard updated")
  }

  def deleteClientCard(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("ClientCard deleted")
  }

}
