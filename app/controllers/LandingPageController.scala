package controllers

import com.google.inject.Inject
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import views.html.LandingPageView

class LandingPageController @Inject() (
    landingPageView: LandingPageView,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def index(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(landingPageView())
    }
}
