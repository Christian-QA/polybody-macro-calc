package controllers

import com.google.inject.Inject
import forms.WhatSexAreYouForm
import play.api.mvc._

import scala.concurrent.Future

class SexController @Inject() (cc: ControllerComponents)
    extends AbstractController(cc) {

  def whatSexAreYouPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.gender())
    }

  def whatSexAreYouOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      WhatSexAreYouForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.HomeController.index())),
          _ => Future.successful(Redirect(routes.HomeController.index()))
        )
    }
}
