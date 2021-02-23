package migrate2dotty.monadwcats

import cats.*
import cats.syntax.flatMap.*
import cats.syntax.functor.*

def compute[F[_]: Monad, A, B](fa: F[A], fb: F[B]): F[(A, B)] =
  for
    a <- fa
    b <- fb
  yield (a, b)
