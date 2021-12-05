package controllers

import com.google.inject.Inject
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import play.cache.DefaultAsyncCacheApi

class FullSummaryController @Inject() (
    cache: DefaultAsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def fullSummaryPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.FullSummary())
    }
}
