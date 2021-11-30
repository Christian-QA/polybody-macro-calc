package controllers

import akka.Done
import com.google.inject.Inject
import forms.{HowMuchDoYouWeighForm, WhatSexAreYouForm}
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc.{
  AbstractController,
  Action,
  AnyContent,
  ControllerComponents,
  Request
}
import play.cache.DefaultAsyncCacheApi

import java.util.concurrent.CompletionStage
import scala.concurrent.Future

class HowMuchDoYouWeighController @Inject() (
    cache: DefaultAsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def howMuchDoYouWeighPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.CurrentWeight(HowMuchDoYouWeighForm.form()))
    }

  def howMuchDoYouWeighOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      HowMuchDoYouWeighForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.HomeController.index())),
          value => {
            val result: CompletionStage[Done] =
              cache.set("currentWeight", value)

            Future.successful(
              Redirect(
                routes.WhatIsYourTargetWeightController
                  .whatIsYourTargetWeightPageLoad()
              )
            )
          }
        )
    }
}
