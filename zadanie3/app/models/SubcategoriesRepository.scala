package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SubcategoriesRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class SubcategoriesTable(tag: Tag) extends Table[Subcategories](tag, "subcategories") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def description: Rep[String] = column[String]("description")

    def * = (id, name, description) <> ((Subcategories.apply _).tupled, Subcategories.unapply)
  }

  val subcategories = TableQuery[SubcategoriesTable]

  def create(name: String, description: String): Future[Subcategories] = db.run {
    (subcategories.map(c => (c.name, c.description))
      returning subcategories.map(_.id)
      into { case ((name, description), id) => Subcategories(id, name, description) }
      ) += (name, description)
  }
    def list(): Future[Seq[Subcategories]] = db.run {
      subcategories.result
    }

    def get(id: Long): Future[Option[Subcategories]] = db.run {
      subcategories.filter(_.id === id).result.headOption
    }

    def update(id: Long, new_subcategories: Subcategories): Future[Unit] = {
      val subcategoriesToUpdate: Subcategories = new_subcategories.copy(id)
      db.run(subcategories.filter(_.id === id).update(subcategoriesToUpdate)).map(_ => ())
    }

    def delete(id: Long): Future[Unit] = db.run(subcategories.filter(_.id === id).delete).map(_ => ())
  }
