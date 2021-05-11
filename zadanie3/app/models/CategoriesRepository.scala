package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CategoriesRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class CategoriesTable(tag: Tag) extends Table[Categories](tag, "categories") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def description: Rep[String] = column[String]("description")
    def * = (id, name,description) <> ((Categories.apply _).tupled, Categories.unapply)
  }

  val categories = TableQuery[CategoriesTable]

  def create(name: String,description: String): Future[Categories] = db.run {
    (categories.map(c => (c.name,c.description))
      returning categories.map(_.id)
      into { case ((name, description), id) => Categories(id, name, description) }
      ) += (name,description)
  }

  def list(): Future[Seq[Categories]] = db.run {
    categories.result
  }

  def get (id: Long): Future[Option[Categories]] = db.run{
    categories.filter(_.id === id).result.headOption
  }

  def update(id: Long, new_category: Categories): Future[Unit] = {
    val categoryToUpdate: Categories = new_category.copy(id)
    db.run(categories.filter(_.id === id).update(categoryToUpdate)).map(_ => ())
  }

  def delete (id: Long): Future[Unit] = db.run(categories.filter(_.id ===id).delete).map(_ => ())
}
