package migrate2dotty.monadwocats

trait Functor[F[_]] {
  def map[A, B](x: F[A])(f: A => B): F[B]
}

trait Monad[F[_]] extends Functor[F] {

  // intrinsic abstract methods for Monad

  def pure[A](a: A): F[A]
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

  // other concrete methods

  override def map[A, B](fa: F[A])(f: A => B): F[B] =
    flatMap(fa)(f andThen pure)

  def flatten[A](fa: F[F[A]]): F[A] =
    flatMap(fa)(identity)
}

object Monad {

  def apply[F[_]](implicit m: Monad[F]): Monad[F] = m

  implicit val listMonad: Monad[List] = new Monad[List] {
    override def pure[A](a: A): List[A] = List(a)
    override def flatMap[A, B](list: List[A])(f: A => List[B]): List[B] =
      list flatMap f
  }

  implicit val optionMonad: Monad[Option] = new Monad[Option] {
    override def pure[A](a: A): Option[A] = Some(a)
    override def flatMap[A, B](
        option: Option[A]
    )(f: A => Option[B]): Option[B] =
      option flatMap f
  }

  implicit def eitherMonad[L]: Monad[Either[L, ?]] = new Monad[Either[L, ?]] {
    def pure[A](a: A): Either[L, A] = Right(a)
    def flatMap[A, B](fa: Either[L, A])(f: A => Either[L, B]): Either[L, B] =
      fa flatMap f
  }

  implicit class MonadSyntax[F[_]: Monad, A](fa: F[A]) {
    def map[B](f: A => B): F[B] =
      Monad[F].map(fa)(f)
    def flatMap[B](f: A => F[B]): F[B] =
      Monad[F].flatMap(fa)(f)
  }
}
