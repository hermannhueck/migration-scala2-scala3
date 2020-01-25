package migrate2dotty

package object joiner {

  final implicit class JoinSyntax[A](private val lhs: A) extends AnyVal {
    @inline def join(rhs: A)(implicit joiner: Joiner[A]): A =
      joiner.join(lhs, rhs)
  }

  final implicit class SeqSyntax[A](private val as: Seq[A]) extends AnyVal {
    @inline def joinAll(implicit joiner: Joiner[A]): A =
      joiner.joinAll(as)
  }
}
