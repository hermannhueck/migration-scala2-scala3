package migrate2dotty.monadwocats

// use _ or ? for wildcard type
trait Functor[F[?]] with
  def [A, B](x: F[A]) map (f: A => B): F[B]


// trait Monad[F[_]] extends Functor[F] // use _ or ? for wildcard type
trait Monad[F[?]] extends Functor[F] with

  // intrinsic abstract methods for Monad

  def pure[A](a: A): F[A]
  def [A, B](fa: F[A]) flatMap (f: A => F[B]): F[B]

  // other concrete methods

  override def [A, B] (fa: F[A]) map (f: A => B): F[B] =
    flatMap(fa)(f andThen pure)

  def [A](fa: F[F[A]]) flatten: F[A] =
    flatMap(fa)(identity)
end Monad

object Monad with

  given Monad[List] with
    override def pure[A](a: A): List[A] = List(a)
    override def [A, B](list: List[A]) flatMap (f: A => List[B]): List[B] =
      list flatMap f

  given as Monad[Option] with
    override def pure[A](a: A): Option[A] = Some(a)
    override def [A, B](option: Option[A]) flatMap (f: A => Option[B]): Option[B] =
      option flatMap f
  
  // given [L]: Monad[[R] =>> Either[L, R]]
  given [L] as Monad[Either[L, *]] with // requires -Ykind-projector
    override def pure[A](a: A): Either[L, A] = Right(a)
    override def [A, B](fa: Either[L, A]) flatMap (f: A => Either[L, B]): Either[L, B] =
      fa flatMap f
end Monad