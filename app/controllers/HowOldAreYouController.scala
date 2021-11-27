package controllers

import com.google.inject.Inject
import forms.WhatSexAreYouForm
import helpers.MaleOrFemale
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc.{
  AbstractController,
  Action,
  AnyContent,
  ControllerComponents,
  Request
}

import scala.concurrent.Future

class HowOldAreYouController @Inject() (
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def howOldAreYouPageLoad(data: List[Any]): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.gender(WhatSexAreYouForm.form()))
    }

  def howOldAreYouOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      WhatSexAreYouForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.HomeController.index())),
          value => Future.successful(Redirect(routes.HomeController.index()))
        )
    }
}
