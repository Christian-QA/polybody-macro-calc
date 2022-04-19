package controllers

import akka.Done
import com.google.inject.Inject
import forms.WhatIsYourTargetWeightForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._
import views.html.Page5TargetWeightView

import scala.concurrent.Future

class Page5WhatIsYourTargetWeightController @Inject() (
    page5TargetWeightView: Page5TargetWeightView,
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def whatIsYourTargetWeightPageLoad(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      Future.successful(
        Ok(page5TargetWeightView(WhatIsYourTargetWeightForm.form()))
      )
    }

  def whatIsYourTargetWeightOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      WhatIsYourTargetWeightForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.LandingPageController.index())),
          value => {
            val result: Future[Done] = cache.set("targetWeight", value.weight)

            Future.successful(
              Redirect(
                routes.Page6HowActiveAreYouController
                  .howActiveAreYouPageLoad()
              )
            )
          }
        )
    }
}
