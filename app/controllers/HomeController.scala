package controllers

import com.google.inject.Inject
import forms.WhatSexAreYouForm
import play.api.i18n.I18nSupport
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */

class HomeController @Inject() (
    cc: ControllerComponents,
    messagesControllerComponents: MessagesControllerComponents
) extends AbstractController(cc)
    with I18nSupport {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.Index())
    }

  def explore() =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.Explore())
    }

  def gender() =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.Gender(WhatSexAreYouForm.form()))
    }

  def age() =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.Age())
    }

  def height() =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.Height())
    }

  def currentWeight() =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.CurrentWeight())
    }

  def targetWeight() =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.TargetWeight())
    }
}
