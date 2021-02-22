
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

object gender extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Welcome")/*3.17*/ {_display_(Seq[Any](format.raw/*3.19*/("""
"""),_display_(/*4.2*/defining(play.core.PlayVersion.current)/*4.41*/ { version =>_display_(Seq[Any](format.raw/*4.54*/("""

"""),_display_(/*6.2*/template("What is your sex?", "radio", param1=Some("male"), param2=Some("female"))),format.raw/*6.84*/("""



""")))}),format.raw/*10.2*/("""
""")))}),format.raw/*11.2*/("""
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
                  DATE: 2021-02-22T21:52:02.911
                  SOURCE: /Users/christianredfern/Documents/Projects/HMRC/Code/Personal/polybody-macro-calc/app/views/gender.scala.html
                  HASH: 1a253a42fcc05e912f9fa11498be19addc8e8d89
                  MATRIX: 723->1|819->4|846->6|869->21|908->23|935->25|982->64|1032->77|1060->80|1162->162|1197->167|1229->169
                  LINES: 21->1|26->2|27->3|27->3|27->3|28->4|28->4|28->4|30->6|30->6|34->10|35->11
                  -- GENERATED --
              */
          