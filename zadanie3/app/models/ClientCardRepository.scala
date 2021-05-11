package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import play.twirl.api.TemplateMagic.anyToDefault
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class ClientCardRepository @Inject() (dbConfigProvider: DatabaseConfigProvider , userRepository: UserRepository)(implicit ec: ExecutionContext)  {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
  import userRepository._

  class ClientCardTable(tag: Tag) extends Table[ClientCard](tag, "clientcard") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def user = column[Long]("user")
    def description = column[String]("description")

    def * = (id, user, description ) <> ((ClientCard.apply _).tupled, ClientCard.unapply)
  }
  private val clientcard = TableQuery[ClientCardTable]
  private val user = TableQuery[UserTable]

  def create(user: Long, description: String): Future[ClientCard] = db.run {
    (clientcard.map(p => (p.user, p.description))
      returning clientcard.map(_.id)
      into {case ((user, description),id) => ClientCard(id, user, description)}
      ) += (user, description)
  }

  def list(): Future[Seq[ClientCard]] = db.run {
    clientcard.result
  }

  def get (id: Long): Future[Option[ClientCard]] = db.run{
    clientcard.filter(_.id === id).result.headOption
  }

  def update(id: Long, new_clientcard: ClientCard): Future[Unit] = {
    val clientcardToUpdate: ClientCard = new_clientcard.copy(id)
    db.run(clientcard.filter(_.id === id).update(clientcardToUpdate)).map(_ => ())
  }

  def delete (id: Long): Future[Unit] = db.run(clientcard.filter(_.id === id).delete).map(_ => ())

}