package controllers

import akka.Done
import com.google.inject.Inject
import forms.WhatSexAreYouForm
import helpers.MaleOrFemale
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import play.cache.DefaultAsyncCacheApi

import java.util.Optional
import java.util.concurrent.CompletionStage
import scala.concurrent.Future

class WhatSexAreYouController @Inject() (
    cache: DefaultAsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def whatSexAreYouPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.gender(WhatSexAreYouForm.form()))
    }

  def whatSexAreYouOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      WhatSexAreYouForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors => {
            Future.successful(Redirect(routes.HomeController.index()))
          },
          value => {
            val result: CompletionStage[Done] = cache.set("sex", value)
            val futureMaybeUser: CompletionStage[Optional[MaleOrFemale]] =
              cache.get[MaleOrFemale]("sex")

            //println(futureMaybeUser)

            Future.successful(
              Redirect(
                routes.HowOldAreYouController
                  .howOldAreYouPageLoad()
              )
            )
          }
        )
    }
}
