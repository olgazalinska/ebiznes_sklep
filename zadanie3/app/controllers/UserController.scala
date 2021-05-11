package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class UserController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
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
