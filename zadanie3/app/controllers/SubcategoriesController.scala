package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class SubcategoriesController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def getSubcategories() = Action { implicit request: Request[AnyContent] =>
    Ok("Subcategories controller")
  }

  def getSubcategory(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Subcategory")
  }

  def createSubcategory() = Action { implicit request: Request[AnyContent] =>
    Created("Subcategory created")
  }

  def updateSubcategory(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Subcategory updated")
  }

  def deleteSubcategory(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Subcategory deleted")
  }

}
