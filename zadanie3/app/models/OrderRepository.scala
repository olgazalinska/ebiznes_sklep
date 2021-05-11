package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class OrderRepository @Inject() (dbConfigProvider: DatabaseConfigProvider , userRepository: UserRepository)(implicit ec: ExecutionContext)  {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
  import userRepository._

  class OrderTable(tag: Tag) extends Table[Order](tag, "order") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def user = column[Long]("user")
    def * = (id, user ) <> ((Order.apply _).tupled, Order.unapply)

  }
  private val order = TableQuery[OrderTable]

  def create(user: Long): Future[Order] = db.run {
    (order.map(o => o.user)
      returning order.map(_.id)
      into {case (user,id) => Order(id, user)}
      ) += user
  }

  def list(): Future[Seq[Order]] = db.run {
    order.result
  }

  def get (id: Long): Future[Option[Order]] = db.run{
    order.filter(_.id === id).result.headOption
  }

  def update(id: Long, new_order: Order): Future[Unit] = {
    val orderToUpdate: Order = new_order.copy(id)
    db.run(order.filter(_.id === id).update(orderToUpdate)).map(_ => ())
  }

  def delete (id: Long): Future[Unit] = db.run(order.filter(_.id === id).delete).map(_ => ())

}