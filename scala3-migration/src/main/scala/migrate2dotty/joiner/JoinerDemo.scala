package migrate2dotty.joiner

import util._
import scala.util.chaining._

import Joiner.{joinAll, given}

@main def JoinerDemo: Unit =

  line().green pipe println

  val x = 25
  val y = 17

  s"${line(10)} joining Int valuess ...".green pipe println

  val iJoined1 = summon[Joiner[Int]].join(x)(y)
  val iJoined2 = Joiner[Int].join(x)(y)
  val iJoined3 = x.join(y)
  val iJoined4 = x join y

  val iJoined = iJoined4
  s"$x join $y = $iJoined" pipe println

  s"${line(10)} joining List[Int] ...".green pipe println
  
  val li1 = List(1,2,3)
  val li2 = List(11,12,13)

  val liJoined1 = summon[Joiner[List[Int]]].join(li1)(li2)
  val liJoined2 = Joiner[List[Int]].join(li1)(li2)
  val liJoined3 = li1.join(li2)
  val liJoined4 = li1 join li2
  
  val liJoined = liJoined4
  s"$li1 join $li2 = $liJoined" pipe println

  s"${line(10)} joining all Int values of a List[Int] ...".green pipe println

  val allIntsJoined = Joiner[Int].joinAll(liJoined: _*)
  val allIntsJoined2 = liJoined.joinAll
  s"all ints joined: $allIntsJoined" pipe println
  s"all ints joined: $allIntsJoined2" pipe println
  
  line().green pipe println
