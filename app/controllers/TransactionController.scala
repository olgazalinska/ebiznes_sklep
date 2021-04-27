package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class TransactionController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getTransactions() = Action { implicit request: Request[AnyContent] =>
    Ok("Transactions controller")
  }

  def getTransaction(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Transaction ")
  }

  def createTransaction() = Action { implicit request: Request[AnyContent] =>
    Created("Transaction created")
  }

  def updateTransaction(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Transaction updated")
  }

  def deleteTransaction(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("Transaction deleted")
  }

}
