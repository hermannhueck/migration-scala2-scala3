package migrate2dotty.joiner

trait Joiner[A] {

  def zero: A
  def join(lhs: A, rhs: A): A

  @inline def joinAll(as: Seq[A]): A =
    as.fold(zero)(join)
}

object Joiner {

  def apply[A: Joiner]: Joiner[A] = implicitly

  implicit val intJoiner: Joiner[Int] = new Joiner[Int] {
    override def zero: Int = 0
    override def join(lhs: Int, rhs: Int): Int =
      lhs + rhs
  }

  implicit def listJoiner[A]: Joiner[List[A]] = new Joiner[List[A]] {
    override def zero: List[A] = List.empty[A]
    override def join(lhs: List[A], rhs: List[A]): List[A] =
      lhs ++ rhs
  }
}
