package controllers

import akka.Done
import com.google.inject.Inject
import forms.WhatIsYourTargetWeightForm
import play.api.cache.AsyncCacheApi
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc._

import java.util.concurrent.CompletionStage
import scala.concurrent.Future

class WhatIsYourTargetWeightController @Inject() (
    cache: AsyncCacheApi,
    cc: ControllerComponents,
    mcc: MessagesApi,
    langs: Langs
) extends AbstractController(cc)
    with I18nSupport {

  def whatIsYourTargetWeightPageLoad(): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok(views.html.TargetWeight(WhatIsYourTargetWeightForm.form()))
    }

  def whatIsYourTargetWeightOnSubmit(): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      WhatIsYourTargetWeightForm
        .form()
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(Redirect(routes.HomeController.index())),
          value => {
            val result: Future[Done] = cache.set("targetWeight", value.weight)

            Future.successful(
              Redirect(
                routes.HowActiveAreYouController
                  .howActiveAreYouPageLoad()
              )
            )
          }
        )
    }
}
