package migrate2dotty.joiner

import util._
import scala.util.chaining._

//import Joiner._

object JoinerDemo extends App {

  line().green pipe println

  val x = 25
  val y = 17

  s"${line(10)} joining Int valuess ...".green pipe println

  val iJoined1 = implicitly[Joiner[Int]].join(x, y)
  val iJoined2 = Joiner[Int].join(x, y)
  val iJoined3 = x.join(y)
  val iJoined4 = x join y

  val iJoined = iJoined4
  s"$x join $y = $iJoined" pipe println

  s"${line(10)} joining List[Int] ...".green pipe println

  val li1 = List(1, 2, 3)
  val li2 = List(11, 12, 13)

  val liJoined1 = implicitly[Joiner[List[Int]]].join(li1, li2)
  val liJoined2 = Joiner[List[Int]].join(li1, li2)
  val liJoined3 = li1.join(li2)
  val liJoined4 = li1 join li2

  val liJoined = liJoined4
  s"$li1 join $li2 = $liJoined" pipe println

  s"${line(10)} joining all Int values of a List[Int] ...".green pipe println

  val allIntsJoined1 = Joiner[Int].joinAll(liJoined)
  val allIntsJoined2 = liJoined.joinAll

  val allIntsJoined = allIntsJoined2
  s"all ints joined: $allIntsJoined" pipe println

  line().green pipe println
}
