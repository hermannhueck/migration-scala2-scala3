package migrate2dotty.macros.use

// See:
//   https://blog.softwaremill.com/starting-with-scala-3-macros-a-short-tutorial-88e9d2b2584c
//   https://github.com/softwaremill/scala3-macro-debug

import scala.util.chaining.*
import migrate2dotty.macros.define.Debug.*
import util.*
import scala.language.implicitConversions

object TestDebugMacro extends App:

  s"${line(10)} inline hello:" pipe println

  hello()

  val z = 2

  def test =
    val x = 0
    val y = 1

    s"${line(10)} inline debugSingle:" pipe println

    debugSingle(x)
    debugSingle(x+y)

    s"${line(10)} inline debug:" pipe println

    debug(x)
    debug(x, y)
    debug(x+y)
    debug(x, x+y)
    debug("A", x, x+y)
    debug("A", x, "B", y)
    debug(x, y, z)

  test
