import play.sbt.PlayImport._
import sbt._
import play.core.PlayVersion.current

object AppDependencies {

  val compile = Seq(
    guice,
    cacheApi,
    ehcache,
    "org.typelevel" %% "cats-core" % "2.0.0",
    "com.typesafe.play" %% "play-json-joda" % "2.9.2",
    "com.lihaoyi" %% "requests" % "0.6.9",
    "com.lihaoyi" %% "upickle" % "1.4.1",
    "org.mongodb.scala" %% "mongo-scala-driver" % "2.9.0",
    "org.reactivemongo" %% "reactivemongo" % "1.0.4",
    "org.reactivemongo" %% "play2-reactivemongo" % "1.0.4-play28"
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test: Seq[ModuleID] = Seq.empty
  }

  object Test {
    def apply(): Seq[ModuleID] =
      new TestDependencies {
        override lazy val test: Seq[sbt.ModuleID] = Seq(
          guice,
          "org.scalatest" %% "scalatest" % "3.2.3",
          "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % scope,
          "org.mockito" % "mockito-core" % "2.28.2" % scope,
          "com.typesafe.play" %% "play-test" % current % scope,
          "org.scalacheck" %% "scalacheck" % "1.14.3" % scope,
          "org.scalatestplus" %% "mockito-3-4" % "3.2.3.0",
          "com.github.tomakehurst" % "wiremock-standalone" % "2.27.2"
        )
      }.test
  }

  def apply(): Seq[ModuleID] = compile ++ Test()
}
