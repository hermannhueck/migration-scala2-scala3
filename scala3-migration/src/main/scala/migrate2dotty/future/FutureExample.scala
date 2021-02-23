package migrate2dotty.future

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration.*
import scala.util.{Failure, Success, Try}

def someComputation(): Int =
  println("computing Int value asynchronously ...")
  Thread.sleep(2000L)
  42

def defTryHandler[A](tryy: Try[A]): Unit = {
  tryy match
    case Success(value)     => s"value = ${value.toString}"
    case Failure(throwable) => s"exception = ${throwable.getMessage}"
} pipe println

val tryHandler = defTryHandler[Int] // eta expansion


import scala.util.chaining.*
import scala.language.implicitConversions
import util.*

@main def FutureExample: Unit =

  line().green pipe println

  import ExecutionContext.Implicits.{given ExecutionContext}
  // given ExecutionContext = ExecutionContext.global

  val future: Future[Int] = Future { someComputation() }

  future onComplete tryHandler

  Thread.sleep(3000L)

  line().green pipe println
end FutureExample
