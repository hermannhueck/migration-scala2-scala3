trait Base

class A extends Base
class B extends Base
class C extends Base

val a = A()
val b = B()
val c = C()

val lBase = List(a, b)
val lab: List[A | B] = List(a, b)

val lBase2 = List(a, b, c)
// val lab2: List[A | B] = List(a, b, c)