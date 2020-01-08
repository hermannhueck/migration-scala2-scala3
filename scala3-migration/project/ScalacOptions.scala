import sbt._

object ScalacOptions {

  val noOptions = Seq.empty

  val dotcOptions = Seq(
    "-encoding",
    "UTF-8",                     // source files are in UTF-8
    "-deprecation", // emit warning and location for usages of deprecated APIs
    "-strict", // use strict type rules, which means some formerly legal code does not typecheck anymore
    "-migration", // with warning and location for migration issues from Scala 2
    "-new-syntax", // require `then` and `do` in control expressions
    "-indent", // allow significant indentation
    "-feature", // emit warning and location for usages of features that should be imported explicitly
    "-Ykind-projector", // allow `*` as wildcard to be compatible with kind projector
    "-Yexplicit-nulls", // make reference types non-nullable. Nullable types can be expressed with unions: e.g. String|Null.
    // "-Yno-kind-polymorphism", // disable kind polymorphism (see https://dotty.epfl.ch/docs/reference/kind-polymorphism.html). Potentially unsound.
  )
}
