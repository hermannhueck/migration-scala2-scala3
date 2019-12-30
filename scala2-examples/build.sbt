import ScalacOptions._

val projectName = "migration-scala2-examples"
val projectDescription =
  "migration: scala2-examples to be migrated to Dotty/Scala3"
val projectVersion = "0.1.0"

val scala2xVersion = "2.13.1"

inThisBuild(
  Seq(
    name := projectName,
    description := projectDescription,
    version := projectVersion,
    scalaVersion := scala2xVersion,
    scalacOptions ++= defaultScalacOptions, // see project/ScalacOptions.scala
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "2.0.0",
      "org.scalatest" %% "scalatest" % "3.1.0" % Test,
      "org.scalacheck" %% "scalacheck" % "1.14.3" % Test,
      "com.novocode" % "junit-interface" % "0.11" % Test,
      compilerPlugin(
        "org.typelevel" % "kind-projector" % "0.11.0" cross CrossVersion.full
      )
    )
  )
)
