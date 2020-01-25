package migrate2dotty.joiner

import scala.annotation.infix

trait Joiner[A] with

  def zero: A
  @infix def (lhs: A) join (rhs: A): A

  inline def (as: Seq[A]) joinAll: A =
    as.fold(zero)(_ join _)
end Joiner

object Joiner with

  def apply[A: Joiner]: Joiner[A] = summon

  given as Joiner[Int] with
    override def zero: Int = 0
    @infix override def (lhs: Int) join (rhs: Int): Int =
      lhs + rhs

  given [A] as Joiner[List[A]] with
    override def zero: List[A] = List.empty[A]
    @infix override def (lhs: List[A]).join(rhs: List[A]): List[A] =
      lhs ++ rhs
end Joiner