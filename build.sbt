import play.core.PlayVersion
import play.core.PlayVersion.current

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """polybody-macro-calc""",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.5",
    PlayKeys.playDefaultPort := 9001,
    libraryDependencies ++= AppDependencies.apply(),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )



