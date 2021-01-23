import ScalacOptions._

val projectName = "migration-scala2-examples"
val projectDescription =
  "migration: scala2-examples to be migrated to Dotty/Scala3"
val projectVersion = "0.1.0"

val scala2xVersion = "2.13.4"

inThisBuild(
  Seq(
    description := projectDescription,
    version := projectVersion,
    scalaVersion := scala2xVersion,
    scalacOptions ++= defaultScalacOptions, // see project/ScalacOptions.scala
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-compiler"  % scala2xVersion,
      "org.scala-lang" % "scala-reflect"   % scala2xVersion,
      "org.typelevel"  %% "cats-effect"    % "2.3.1",
      "org.scalatest"  %% "scalatest"      % "3.2.3" % Test,
      "org.scalacheck" %% "scalacheck"     % "1.15.2" % Test,
      "com.novocode"   % "junit-interface" % "0.11" % Test,
      compilerPlugin(
        "org.typelevel" % "kind-projector" % "0.11.3" cross CrossVersion.full
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

lazy val root = project
  .in(file("."))
  .settings(
    name := projectName
  )
