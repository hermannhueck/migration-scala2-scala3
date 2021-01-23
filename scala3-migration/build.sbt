import ScalacOptions._

val projectName        = "scala3-migration"
val projectDescription = "migration: examples migrated to Dotty/Scala3"
val projectVersion     = "0.1.0"

val dottyVersion = "3.0.0-M3"
// val dottyVersion = dottyLatestNightlyBuild.get

inThisBuild(
  Seq(
    description := projectDescription,
    version := projectVersion,
    scalaVersion := dottyVersion,
    scalacOptions ++= noOptions,   // see: project/ScalacOptions.scala
    scalacOptions ++= dotcOptions, // see: project/ScalacOptions.scala
    libraryDependencies ++=
      Seq(
        "org.typelevel"  %% "cats-effect" % "2.1.3",
        "org.scalameta"  %% "munit"       % "0.7.5",
        "org.scalacheck" %% "scalacheck"  % "1.14.3" % Test
      ).map(_.withDottyCompat(scalaVersion.value)) ++
        Seq(
          "com.novocode" % "junit-interface" % "0.11" % Test
        )
  )
)

lazy val root = project
  .in(file("."))
  .settings(
    name := projectName
  )
