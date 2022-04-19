package controllers

import com.google.inject.Inject
import forms.ShortSummaryForm
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import services.CacheService
import views.html.{LandingPage, Page7ShortSummary}

import scala.concurrent.Future

class Page7ShortSummaryController @Inject() (
    cacheService: CacheService,
    page7ShortSummary: Page7ShortSummary,
    landingPage: LandingPage, //TODO - Change to error
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def shortSummaryPageLoad(): Action[AnyContent] = {
    Action.async { implicit request: Request[AnyContent] =>
      println(cacheService.cacheToShortDto)
      cacheService.cacheToShortDto match {
        case Some(value) =>
          println("1" * 100)
          println(value)
          Future.successful(
            Ok(page7ShortSummary(ShortSummaryForm.form(), value))
          )
        case None =>
          println("2" * 100)
          Future.successful(Ok(landingPage()))

        //BadRequest(views.html.errorViews.ErrorBadRequestView)
      }
    }
  }

  def shortSummaryOnSubmit(): Action[AnyContent] = {
    Action.async { implicit request: Request[AnyContent] =>
      ShortSummaryForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.LandingPageController.index())),
          value =>
            if (value.continue) {
              Future.successful(
                Redirect(
                  routes.Page8DoYouHaveAKcalGoalController
                    .doYouHaveAKcalGoalPageLoad()
                )
              )
            } else {
              Future.successful(
                Redirect(
                  routes.LandingPageController.index()
                )
              )
            }
        )
    }
  }
}
