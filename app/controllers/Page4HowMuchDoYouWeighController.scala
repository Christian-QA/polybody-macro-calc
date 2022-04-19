package controllers

import akka.Done
import com.google.inject.Inject
import forms.HowMuchDoYouWeighForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import views.html.Page4CurrentWeightView

import java.util.concurrent.CompletionStage
import scala.concurrent.Future

class Page4HowMuchDoYouWeighController @Inject() (
    page4CurrentWeightView: Page4CurrentWeightView,
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def howMuchDoYouWeighPageLoad(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      Future.successful(
        Ok(page4CurrentWeightView(HowMuchDoYouWeighForm.form()))
      )
    }

  def howMuchDoYouWeighOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      HowMuchDoYouWeighForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.LandingPageController.index())),
          value => {
            val result: Future[Done] =
              cache.set("currentWeight", value.weight)

            Future.successful(
              Redirect(
                routes.Page5WhatIsYourTargetWeightController
                  .whatIsYourTargetWeightPageLoad()
              )
            )
          }
        )
    }
}
