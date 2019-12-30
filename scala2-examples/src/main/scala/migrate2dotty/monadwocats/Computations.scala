package migrate2dotty.monadwocats

object Computations {

  import Monad.MonadSyntax

  def compute[F[_]: Monad, A, B](fa: F[A], fb: F[B]): F[(A, B)] =
    for {
      a <- fa
      b <- fb
    } yield (a, b)
}
