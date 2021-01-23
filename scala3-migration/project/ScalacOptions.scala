import sbt._

object ScalacOptions {

  val noOptions = Seq.empty

  val dotcOptions = Seq(
    "-encoding",
    "UTF-8",            // source files are in UTF-8
    "-deprecation",     // emit warning and location for usages of deprecated APIs
    "-explain",         // Explain errors in more detail.
    "-explain-types",   // Explain type errors in more detail.
    "-new-syntax",      // require `then` and `do` in control expressions
    "-indent",          // allow significant indentation
    "-feature",         // emit warning and location for usages of features that should be imported explicitly
    "-print-lines",     // Show source code line numbers.
    "-Ykind-projector", // allow `*` as wildcard to be compatible with kind projector
    "-Yexplicit-nulls"  // make reference types non-nullable. Nullable types can be expressed with unions: e.g. String|Null.
  )
}
