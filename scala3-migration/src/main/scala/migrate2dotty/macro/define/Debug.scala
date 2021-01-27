package migrate2dotty.macros.define

// See:
//   https://blog.softwaremill.com/starting-with-scala-3-macros-a-short-tutorial-88e9d2b2584c
//   https://github.com/softwaremill/scala3-macro-debug

object Debug:

  import scala.quoted._

  inline def hello(): Unit =
    println("Hello, world!")
  
  // -- 
  
  inline def debugSingle(inline expr: Any): Unit =
    ${debugSingleImpl('expr)} 
  
  private def debugSingleImpl(expr: Expr[Any])(using Quotes): Expr[Unit] = 
    '{ println("Value of " + ${Expr(expr.show)} + " is " + $expr) }
  
  // --

  inline def debug(inline exprs: Any*): Unit = ${debugImpl('exprs)}

  private def debugImpl(exprs: Expr[Seq[Any]])(using Quotes): Expr[Unit] = 

    def showWithValue(e: Expr[_]): Expr[String] =
     '{${Expr(e.show)} + " = " + $e}
  
    val stringExps: Seq[Expr[String]] = exprs match 
      case Varargs(es) => 
        es.map { e =>
          if e.isExprOf[String] then
            e.asInstanceOf[Expr[String]]
          else
            showWithValue(e)
          // case Const(s: String) => Expr(s) // deprecated since 3.0.0-M3
          // case e => showWithValue(e)
        }
      case e => List(showWithValue(e))
  
    val concatenatedStringsExp =
      stringExps.reduceOption((e1, e2) => '{$e1 + ", " + $e2}).getOrElse('{""})

    '{println($concatenatedStringsExp)}

