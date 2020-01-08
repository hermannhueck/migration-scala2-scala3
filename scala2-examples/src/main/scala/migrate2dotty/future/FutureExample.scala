package migrate2dotty.future

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

import scala.util.chaining._
import util._

object FutureExample extends App {

  line().green pipe println

  // import ExecutionContext.Implicits.global
  implicit val ec: ExecutionContext = ExecutionContext.global // Scala 2

  def someComputation(): Int = {
    println("computing Int value asynchronously ...")
    Thread.sleep(2000L)
    42
  }

  val future: Future[Int] = Future { someComputation() }

  def defTryHandler[A](tryy: Try[A]): Unit = {
    tryy match {
      case Success(value)     => s"value = ${value.toString}"
      case Failure(throwable) => s"exception = ${throwable.getMessage}"
    }
  } pipe println

  val tryHandler = defTryHandler[Int] _ // eta expansion

  future onComplete tryHandler

  Thread.sleep(3000L)

  line().green pipe println
}
