package controllers

import akka.Done
import com.google.inject.Inject
import forms.{HowActiveAreYouForm, WhatSexAreYouForm}
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

class HowActiveAreYouController @Inject() (
    cache: DefaultAsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def howActiveAreYouPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.ActivityLevel(HowActiveAreYouForm.form()))
    }

  def howActiveAreYouOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      HowActiveAreYouForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.HomeController.index())),
          value => {
            val result: CompletionStage[Done] =
              cache.set("activityLevel", value)

            Future.successful(
              Redirect(
                routes.ShortSummaryController
                  .shortSummaryPageLoad()
              )
            )
          }
        )
    }
}
