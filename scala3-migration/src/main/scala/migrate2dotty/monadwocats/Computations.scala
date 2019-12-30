package migrate2dotty.monadwocats

def compute[F[?]: Monad, A, B](fa: F[A], fb: F[B]): F[(A, B)] =
  for
    a <- fa
    b <- fb
  yield (a, b)
