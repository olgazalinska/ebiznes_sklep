package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class CategoriesController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
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
