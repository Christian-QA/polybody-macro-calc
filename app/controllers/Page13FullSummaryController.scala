package controllers

import com.google.inject.Inject
import controllers.handler.ErrorHandler
import errors.CustomTimeoutResponse
import forms.FullSummaryForm
import models.MacroStat
import play.api.data.Form
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import services.{CacheService, MacroStatService}
import views.html.{LandingPageView, Page13FullSummaryView}

import java.time.LocalDate
import scala.concurrent.{ExecutionContext, Future}

class Page13FullSummaryController @Inject() (
    macroStatService: MacroStatService,
    cacheService: CacheService,
    errorHandler: ErrorHandler,
    page13FullSummaryView: Page13FullSummaryView,
    landingPage: LandingPageView, //TODO - Change to error
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
)(implicit ec: ExecutionContext)
    extends AbstractController(cc)
    with I18nSupport {

  def fullSummaryPageLoad(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      println(cacheService.cacheToFullDto)
      cacheService.cacheToFullDto match {
        case Some(value) =>
          println("1" * 100)
          println(value)
          Future.successful(
            Ok(page13FullSummaryView(FullSummaryForm.form(), value))
          )
        case None =>
          println("2" * 100)
          errorHandler.handle(CustomTimeoutResponse)
      }
    }

  def fullSummarySaveData(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      FullSummaryForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.LandingPageController.index())),
          value => {
            if (value.save) {
              cacheService.cacheToFullDto match {
                case Some(data) =>
                  val macroStat: MacroStat = MacroStat(
                    Some(LocalDate.now()),
                    data.activityLevel,
                    data.setGoal,
                    data.proteinPreference,
                    data.fatPreference,
                    data.carbPreference,
                    data.bodyFat,
                    Some("Default"),
                    1000,
                    500,
                    10
                  )
                  macroStatService
                    .addMacroStat(
                      "Calvin", // TODO Change name to username
                      macroStat
                    )
                    .flatMap {
                      case Left(error) =>
                        println("1" * 100)
                        println(error)
                        println("1" * 100)

                        errorHandler.handle(error)
                      case Right(value) =>
                        Future.successful(
                          Redirect(
                            routes.Page14WeightSubmitController
                              .wouldYouLikeToSubmitThisWeightPageLoad()
                          )
                        )
                    }
                case None =>
                  errorHandler.handle(CustomTimeoutResponse)
              }
            } else {
              Future.successful(
                Redirect(
                  routes.Page14WeightSubmitController
                    .wouldYouLikeToSubmitThisWeightPageLoad()
                )
              )
            }
          }
        )
    }
}
