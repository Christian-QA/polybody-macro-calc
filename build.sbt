import play.core.PlayVersion

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
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0",
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



