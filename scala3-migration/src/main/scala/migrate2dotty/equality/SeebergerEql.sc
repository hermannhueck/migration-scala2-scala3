// See also: https://heikoseeberger.rocks/2020/01/07/2020-01-07-dotty-4/

final case class Foo()

Foo() == Foo()
Foo() == Option(Foo())

import scala.language.strictEquality

Foo() == Foo()
Foo() == Option(Foo())

given Eql[Foo, Foo] = Eql.derived

Foo() == Foo()
Foo() == Option(Foo())

final case class Bar() derives Eql

Bar() == Bar()
Bar() == Option(Bar())

Foo() == Bar()
Bar() == Foo()

given Eql[Foo, Bar] = Eql.derived
given Eql[Bar, Foo] = Eql.derived

Foo() == Bar()
Bar() == Foo()

// The Scala standard library provides
// some of these bidirectional Eql instances
// for numeric types:

42 == 42L
42 == 42.0

// Strict/multiversial equality is opt-in.
// if unwanted (for backward compatibility)
// just omit the language feature
// "scala.language.strictEquality"
// and the compiler flag
// -language:strictEquality
//
// In this case the compiler provides
// an Eql instance to compare any two types:
//
def eqlAny[L, R]: Eql[L, R] = Eql.derived
given Eql[Any, Any] = eqlAny

Foo() == Foo()
Foo() == Option(Foo())
