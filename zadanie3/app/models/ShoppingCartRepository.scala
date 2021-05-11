package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class ShoppingCartRepository  @Inject() (dbConfigProvider: DatabaseConfigProvider , userRepository: UserRepository, orderRepository: OrderRepository)(implicit ec: ExecutionContext)  {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
  import userRepository._
  import orderRepository._

  class ShoppingCartTable(tag: Tag) extends Table[ShoppingCart](tag, "shoppingcart") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def user = column[Long]("user")
    def order = column[Long]("order")
    def price = column[Double]("price")

    def * = (id, user ,order, price  ) <> ((ShoppingCart.apply _).tupled, ShoppingCart.unapply)

  }

  private val user = TableQuery[UserTable]
  private val order = TableQuery[OrderTable]
  val shoppingcart = TableQuery[ShoppingCartTable]

  def add(user: Long, order: Long, price: Double): Future[ShoppingCart] = db.run {
    (shoppingcart.map(c => (c.user, c.order,c.price))
      returning shoppingcart.map(_.id)
      into { case ((user, order, price), id) => ShoppingCart(id, user, order, price) }
      ) += (user, order, price)
  }

  def get(id: Long): Future[Option[ShoppingCart]] = db.run {
    shoppingcart.filter(_.id === id).result.headOption
  }

  def update(id: Long, new_shoppingcart: ShoppingCart): Future[Unit] = {
    val shoppingcartToUpdate: ShoppingCart = new_shoppingcart.copy(id)
    db.run(shoppingcart.filter(_.id === id).update(shoppingcartToUpdate)).map(_ => ())
  }

  def delete (id: Long): Future[Unit] = db.run(shoppingcart.filter(_.id === id).delete).map(_ => ())

}