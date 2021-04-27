package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class CategoriesController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getCategories() = Action { implicit request: Request[AnyContent] =>
    Ok("Categories controller")
  }

  def getCategory(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Category")
  }

  def createCategory() = Action { implicit request: Request[AnyContent] =>
    Created("Category created")
  }

  def updateCategory(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Category updated")
  }

  def deleteCategory(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Category deleted")
  }

}
