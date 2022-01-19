package controllers

import akka.Done
import com.google.inject.Inject
import forms.WhatSexAreYouForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._

import java.util.concurrent.CompletionStage
import scala.concurrent.Future

class WhatSexAreYouController @Inject() (
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def whatSexAreYouPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.Sex(WhatSexAreYouForm.form()))
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
            val result: Future[Done] =
              cache.set("sex", value.sex)

            Future.successful(
              Redirect(
                routes.WhenWereYouBornController
                  .whenWereYouBornPageLoad()
              )
            )
          }
        )
    }
}
