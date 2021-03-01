
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object height extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.4*/("""

"""),_display_(/*3.2*/main("Height - Polybody")/*3.27*/ {_display_(Seq[Any](format.raw/*3.29*/("""
"""),_display_(/*4.2*/defining(play.core.PlayVersion.current)/*4.41*/ { version =>_display_(Seq[Any](format.raw/*4.54*/("""

"""),_display_(/*6.2*/template("What is your height?", "text", param3=Some(5))),format.raw/*6.58*/("""

""")))}),format.raw/*8.2*/("""
""")))}),format.raw/*9.2*/("""
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-03-01T20:07:26.618
                  SOURCE: C:/Users/felix/source/repos/polybody-macro-calc/app/views/height.scala.html
                  HASH: 6c3a1c6cbe59552ba792b43e99e4520b84dde1f0
                  MATRIX: 723->1|819->3|849->8|882->33|921->35|949->38|996->77|1046->90|1076->95|1152->151|1186->156|1218->159
                  LINES: 21->1|26->1|28->3|28->3|28->3|29->4|29->4|29->4|31->6|31->6|33->8|34->9
                  -- GENERATED --
              */
          