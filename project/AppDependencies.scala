import AppDependencies.TestDependencies
import sbt._

private object AppDependencies {
  import play.core.PlayVersion
  import play.sbt.PlayImport._

  val compile = Seq(
    "org.typelevel"       %% "cats-core"        % "2.0.0",
    "com.typesafe.play"   %% "play-json-joda"   % "2.8.7"
  )



  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test: Seq[ModuleID] = Seq.empty

    val scalaTestPlusVersion = "3.1.3"
  }

  object Test {
    def apply() = new TestDependencies {
      override lazy val test = Seq(
        "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlusVersion % scope,
        "org.mockito"            %  "mockito-all"        % "1.9.5" % scope,
        "com.typesafe.play"      %% "play-test"          % PlayVersion.current % scope,
        "org.scalacheck"         %% "scalacheck"         % "1.14.3" % scope
      )
    }.test
  }


}
