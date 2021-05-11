package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProductsRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class ProductsTable(tag: Tag) extends Table[Products](tag, "products") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")

    def * = (id , name ) <> ((Products.apply _).tupled, Products.unapply)
  }

  val products = TableQuery[ProductsTable]

  def create(name: String): Future[Products] = db.run {
    (products.map(c => c.name)
      returning products.map(_.id)
      into {case (name, id) => Products(id, name)}
      ) += name
  }

  def list(): Future[Seq[Products]] = db.run {
    products.result
  }

  def get (id: Long): Future[Option[Products]] = db.run{
    products.filter(_.id === id).result.headOption
  }

  def update(id: Long, new_products: Products): Future[Unit] = {
    val productsToUpdate: Products = new_products.copy(id)
    db.run(products.filter(_.id === id).update(productsToUpdate)).map(_ => ())
  }

  def delete (id: Long): Future[Unit] = db.run(products.filter(_.id ===id).delete).map(_ => ())
}
