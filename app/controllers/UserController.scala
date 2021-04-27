package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class UserController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getUsers() = Action { implicit request: Request[AnyContent] =>
    Ok("Users controller")
  }

  def getUser(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("User")
  }

  def createUser() = Action { implicit request: Request[AnyContent] =>
    Created("User created")
  }

  def updateUser(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("User updated")
  }

  def deleteUser(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("User deleted")
  }

}
