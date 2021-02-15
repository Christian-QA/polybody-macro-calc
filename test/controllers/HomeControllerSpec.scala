package controllers

import com.google.inject.Inject
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.mvc.Results.Ok

import scala.Option.when

class HomeControllerSpec extends PlaySpec with Inject {

  val homeController = Inject[HomeController]
  val controllerComponents = Inject[ControllerComponents]


  "HomeController" must {

    "display index page when index function is called" in {

      val viewIndex = homeController.index().thenReturn(Ok(views.html.index()))

      viewIndex mustBe Ok(views.html.index())

    }


  }



}
