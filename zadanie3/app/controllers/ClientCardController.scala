package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class ClientCardController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
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
