package utils

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.i18n.{Lang, Langs, Messages, MessagesApi}
import play.api.mvc.{ControllerComponents, MessagesControllerComponents}
import play.api.test.Injecting

import scala.concurrent.ExecutionContext

class BaseSpec
    extends AnyWordSpec
    with GuiceOneAppPerSuite
    with MockitoSugar
    with Matchers
    with Injecting
    with ScalaFutures {

  implicit lazy val ec: ExecutionContext = inject[ExecutionContext]

  lazy val cc: ControllerComponents = inject[ControllerComponents]

  lazy val mcc: MessagesControllerComponents =
    inject[MessagesControllerComponents]

  val messages: MessagesApi = inject[MessagesApi]
}
