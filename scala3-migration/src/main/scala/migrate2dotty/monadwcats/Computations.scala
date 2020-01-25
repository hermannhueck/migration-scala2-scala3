package migrate2dotty.monadwcats

import cats._
import cats.syntax.flatMap._
import cats.syntax.functor._

import scala.language.implicitConversions

def compute[F[?]: Monad, A, B](fa: F[A], fb: F[B]): F[(A, B)] =
  for
    a <- fa
    b <- fb
  yield (a, b)
