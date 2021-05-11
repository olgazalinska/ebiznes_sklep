package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class PromotionsRepository  @Inject() (dbConfigProvider: DatabaseConfigProvider , productsRepository: ProductsRepository)(implicit ec: ExecutionContext)  {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
  import productsRepository._

  class PromotionsTable(tag: Tag) extends Table[Promotions](tag, "promotions") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def product_id = column[Long]("user")
    def discount = column[Long]("order")

    def * = (id, product_id ,discount ) <> ((Promotions.apply _).tupled, Promotions.unapply)

  }

  private val products = TableQuery[ProductsTable]
  val promotions = TableQuery[PromotionsTable]

  def add(id: Long, product_id: Long, discount: Long): Future[Promotions] = db.run {
    (promotions .map(c => (c.product_id,c.discount))
      returning promotions.map(_.id)
      into { case ((product_id, discount), id) => Promotions(id, product_id, discount) }
      ) += (product_id, discount)
  }

  def get(id: Long): Future[Option[Promotions]] = db.run {
    promotions.filter(_.id === id).result.headOption
  }

  def update(id: Long, new_shoppingcart: Promotions): Future[Unit] = {
    val shoppingcartToUpdate: Promotions = new_shoppingcart.copy(id)
    db.run(promotions.filter(_.id === id).update(shoppingcartToUpdate)).map(_ => ())
  }

  def delete (id: Long): Future[Unit] = db.run(promotions.filter(_.id === id).delete).map(_ => ())

}