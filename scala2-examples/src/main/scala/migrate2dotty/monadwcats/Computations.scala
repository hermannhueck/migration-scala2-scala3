package migrate2dotty.monadwcats

import cats._

import cats.syntax.flatMap._
import cats.syntax.functor._

object Computations {

  def compute[F[_]: Monad, A, B](fa: F[A], fb: F[B]): F[(A, B)] =
    for {
      a <- fa
      b <- fb
    } yield (a, b)
}
