package controllers

import akka.Done
import com.google.inject.Inject
import forms.WhatSexAreYouForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import views.html.Page1SexView

import scala.concurrent.Future

class Page1WhatSexAreYouController @Inject() (
    page1SexView: Page1SexView,
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def whatSexAreYouPageLoad(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      Future.successful(Ok(page1SexView(WhatSexAreYouForm.form())))
    }

  def whatSexAreYouOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      WhatSexAreYouForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors => {
            Future.successful(
              BadRequest(
                page1SexView(formWithErrors)
              )
            )
          },
          value => {
            val result: Future[Done] =
              cache.set("sex", value.sex)

            Future.successful(
              Redirect(
                routes.Page2WhenWereYouBornController
                  .whenWereYouBornPageLoad()
              )
            )
          }
        )
    }
}
