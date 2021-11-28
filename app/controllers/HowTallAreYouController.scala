package controllers

import akka.Done
import com.google.inject.Inject
import forms.WhatSexAreYouForm
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

class HowTallAreYouController @Inject() (
    cache: DefaultAsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def howTallAreYouPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.Height())
    }

  def howTallAreYouOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      WhatSexAreYouForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.HomeController.index())),
          value => {
            val result: CompletionStage[Done] = cache.set("age", value)

            Future.successful(
              Redirect(
                routes.HowMuchDoYouWeighController.howMuchDoYouWeighPageLoad()
              )
            )
          }
        )
    }
}
