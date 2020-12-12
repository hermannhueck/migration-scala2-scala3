import ScalacOptions._

val projectName = "migration-scala2-examples"
val projectDescription =
  "migration: scala2-examples to be migrated to Dotty/Scala3"
val projectVersion = "0.1.0"

val scala2xVersion = "2.13.4"

inThisBuild(
  Seq(
    name := projectName,
    description := projectDescription,
    version := projectVersion,
    scalaVersion := scala2xVersion,
    scalacOptions ++= defaultScalacOptions, // see project/ScalacOptions.scala
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-compiler"  % scala2xVersion,
      "org.scala-lang" % "scala-reflect"   % scala2xVersion,
      "org.typelevel"  %% "cats-effect"    % "2.3.0",
      "org.scalatest"  %% "scalatest"      % "3.2.3" % Test,
      "org.scalacheck" %% "scalacheck"     % "1.15.1" % Test,
      "com.novocode"   % "junit-interface" % "0.11" % Test,
      compilerPlugin(
        "org.typelevel" % "kind-projector" % "0.11.2" cross CrossVersion.full
      )
    )
  )
)
