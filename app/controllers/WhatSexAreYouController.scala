package controllers

import com.google.inject.Inject
import forms.WhatSexAreYouForm
import helpers.MaleOrFemale
import play.api.data.Form
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._

import scala.concurrent.Future

class WhatSexAreYouController @Inject() (
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
          formWithErrors =>
            Future.successful(Redirect(routes.HomeController.index())),
          value =>
            Future.successful(
              Redirect(
                routes.HowOldAreYouController
                  .howOldAreYouPageLoad(List(value.sex))
              )
            )
        )
    }
}