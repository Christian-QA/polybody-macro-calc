package utils

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.guice.GuiceOneAppPerSuite

class BaseSpec extends AnyWordSpec with GuiceOneAppPerSuite with MockitoSugar with Matchers {

}
