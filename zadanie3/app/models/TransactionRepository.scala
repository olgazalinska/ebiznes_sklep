package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class TransactionRepository  @Inject() (dbConfigProvider: DatabaseConfigProvider , userRepository: UserRepository, orderRepository: OrderRepository)(implicit ec: ExecutionContext)  {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
  import userRepository._
  import orderRepository._

  class TransactionTable(tag: Tag) extends Table[Transaction](tag, "transaction") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def user = column[Long]("user")
    def order = column[Long]("order")
    def status = column[Int]("price")

    def * = (id, user ,order, status  ) <> ((Transaction.apply _).tupled, Transaction.unapply)

  }

  private val user = TableQuery[UserTable]
  private val order = TableQuery[OrderTable]
  val transaction = TableQuery[TransactionTable]

  def add(user: Long, order: Long, status: Int): Future[Transaction] = db.run {
    (transaction.map(c => (c.user, c.order,c.status))
      returning transaction.map(_.id)
      into { case ((user, order, status), id) => Transaction(id, user, order, status) }
      ) += (user, order, status)
  }

  def get(id: Long): Future[Option[Transaction]] = db.run {
    transaction.filter(_.id === id).result.headOption
  }

  def update(id: Long, new_transaction: Transaction): Future[Unit] = {
    val transactionToUpdate: Transaction = new_transaction.copy(id)
    db.run(transaction.filter(_.id === id).update(transactionToUpdate)).map(_ => ())
  }

  def delete (id: Long): Future[Unit] = db.run(transaction.filter(_.id === id).delete).map(_ => ())

}