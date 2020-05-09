import ScalacOptions._

val projectName = "migration-scala2-examples"
val projectDescription =
  "migration: scala2-examples to be migrated to Dotty/Scala3"
val projectVersion = "0.1.0"

val scala2xVersion = "2.13.2"

inThisBuild(
  Seq(
    name := projectName,
    description := projectDescription,
    version := projectVersion,
    scalaVersion := scala2xVersion,
    scalacOptions ++= defaultScalacOptions, // see project/ScalacOptions.scala
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-compiler" % scala2xVersion,
      "org.scala-lang" % "scala-reflect"  % scala2xVersion,
      "org.typelevel"  %% "cats-effect"   % "2.1.3",
      "org.scalameta"  %% "munit"         % "0.7.5",
      "org.scalacheck" %% "scalacheck"    % "1.14.3" % Test,
      compilerPlugin( // https://github.com/typelevel/kind-projector
        "org.typelevel" % "kind-projector" % "0.11.0" cross CrossVersion.full
      ),
      compilerPlugin( // https://github.com/oleg-py/better-monadic-for
        compilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
      )
    ),
    testFrameworks += new TestFramework("munit.Framework"),
    initialCommands :=
      s"""|
          |import scala.util.chaining._
          |import cats._, cats.data._, cats.implicits._
          |println
          |""".stripMargin
  )
)

Compile / console / scalacOptions --= scalcOptionsToRemoveForConsole
