package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class VoucherRepository @Inject() (dbConfigProvider: DatabaseConfigProvider , userRepository: UserRepository)(implicit ec: ExecutionContext)  {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
  import userRepository._

  class VoucherTable(tag: Tag) extends Table[Voucher](tag, "voucher") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def user = column[Long]("user")
    def * = (id, user ) <> ((Voucher.apply _).tupled, Voucher.unapply)

  }
  private val voucher = TableQuery[VoucherTable]

  def create(user: Long): Future[Voucher] = db.run {
    (voucher.map(o => o.user)
      returning voucher.map(_.id)
      into {case (user,id) => Voucher(id, user)}
      ) += user
  }

  def list(): Future[Seq[Voucher]] = db.run {
    voucher.result
  }

  def get (id: Long): Future[Option[Voucher]] = db.run{
    voucher.filter(_.id === id).result.headOption
  }

  def update(id: Long, new_voucher: Voucher): Future[Unit] = {
    val voucherToUpdate: Voucher = new_voucher.copy(id)
    db.run(voucher.filter(_.id === id).update(voucherToUpdate)).map(_ => ())
  }

  def delete (id: Long): Future[Unit] = db.run(voucher.filter(_.id === id).delete).map(_ => ())

}