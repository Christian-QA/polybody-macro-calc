//import sbt._
//
//private object AppDependencies {
//  import play.core.PlayVersion
//  import play.sbt.PlayImport._
//
//  val compile = Seq(
//    "org.typelevel"       %% "cats-core"        % "2.0.0",
//    "com.typesafe.play"   %% "play-json-joda"   % "2.8.7"
//  )
//
//  trait TestDependencies {
//    lazy val scope: String = "test"
//    lazy val test: Seq[ModuleID] = Seq.empty
//  }
//
//  object Test {
//    def apply() = new TestDependencies {
//      override lazy val test = Seq(
//        guice,
//        "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % scope,
//        "org.mockito"            %  "mockito-core"        % "2.28.2" % scope,
//        "com.typesafe.play"      %% "play-test"          % PlayVersion.current % scope,
//        "org.scalacheck"         %% "scalacheck"         % "1.14.3" % scope,
//        "uk.gov.uk"              %% "hmrctest"           % "3.9.0-play27" % scope
//      )
//    }.test
//  }
//
//  def apply() = compile ++ Test()
//
//}
