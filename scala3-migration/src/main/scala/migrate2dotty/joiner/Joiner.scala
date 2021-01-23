package migrate2dotty.joiner

trait Joiner[A]:

  def zero: A

  extension (lhs: A)
    def join (rhs: A): A

  extension (as: Seq[A])
    def joinAll: A = as.fold(zero)(_ join _)

object Joiner:

  def apply[A: Joiner]: Joiner[A] = summon

  given Joiner[Int] with
    def zero: Int = 0
    extension (lhs: Int)
      def join (rhs: Int): Int = lhs + rhs

  given[A] (using Joiner[A]): Joiner[List[A]] with
    def zero: List[A] = List.empty[A]
    extension (lhs: List[A])
      def join(rhs: List[A]): List[A] = lhs ++ rhs
