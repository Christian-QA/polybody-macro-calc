package utils

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, Suite}
import play.api.Application
import play.api.inject.Injector
import play.api.inject.guice.GuiceApplicationBuilder

trait WireMockHelper extends BeforeAndAfterAll with BeforeAndAfterEach {
  this: Suite =>

  protected val server: WireMockServer = new WireMockServer(
    wireMockConfig().dynamicPort()
  )

  protected lazy val app: Application =
    new GuiceApplicationBuilder()
      .configure(
        "microservice.polybody-backend.port" -> server.port(),
        "microservice.polybody-backend.host" -> "127.0.0.1"
      )
      .build()

  protected lazy val injector: Injector = app.injector

  override def beforeAll(): Unit = {
    server.start()
    super.beforeAll()
  }

  override def beforeEach(): Unit = {
    server.resetAll()
    super.beforeEach()
  }

  override def afterAll(): Unit = {
    super.afterAll()
    server.stop()
  }
}
