package migrate2dotty.monadwocats

import util._

object MonadExample extends App {

  import Computations._

  printStartLine()

  println("----- List:")

  val l1 = List(1, 2, 3)
  val l2 = List(10, 20, 30)

  val lResult = compute(l1, l2)
  println(lResult)

  val l3: List[List[Int]] =
    l1.map(i => l2.map(_ + i))
  println(l3)
  val lResult3 = l3.flatten
  println(lResult3)

  println("----- Option:")

  val o1 = Option(1)
  val o2 = Option(10)

  val oResult = compute(o1, o2)
  println(oResult)

  println("----- Either:")

  val e1 = Right(1).withLeft[String]
  val e2 = Right(10).withLeft[String]

  val eResult = compute(e1, e2)
  println(eResult)

  printEndLine()
}
