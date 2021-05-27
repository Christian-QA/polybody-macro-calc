import play.core.PlayVersion
import play.core.PlayVersion.current

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """polybody-macro-calc""",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.5",
    libraryDependencies ++= Seq(
      "org.typelevel"       %% "cats-core"        % "2.0.0",
      "com.typesafe.play"   %% "play-json-joda"   % "2.9.2",
      guice,
      "org.scalatest"           %% "scalatest"                % "3.2.3",
      "com.typesafe.play"       %% "play-test"                % current,
      "org.scalatestplus.play"  %% "scalatestplus-play"       % "5.1.0",
      "org.scalatestplus"       %% "mockito-3-4"              % "3.2.3.0",
      "org.mockito"            %  "mockito-core"        % "2.28.2",
      "com.typesafe.play"      %% "play-test"          % PlayVersion.current,
      "org.scalacheck"         %% "scalacheck"         % "1.14.3"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )



