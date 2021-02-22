
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

object template extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*2.2*/defining(play.core.PlayVersion.current)/*2.41*/ { version =>_display_(Seq[Any](format.raw/*2.54*/("""
"""),format.raw/*3.1*/("""<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <title>Signin Template · Bootstrap v5.0</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">



    <!-- Bootstrap core CSS -->
    <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <style>

    .container """),format.raw/*22.16*/("""{"""),format.raw/*22.17*/("""
        """),format.raw/*23.9*/("""display: flex;
        justify-content: center;
    """),format.raw/*25.5*/("""}"""),format.raw/*25.6*/("""

      """),format.raw/*27.7*/(""".bd-placeholder-img """),format.raw/*27.27*/("""{"""),format.raw/*27.28*/("""
        """),format.raw/*28.9*/("""font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      """),format.raw/*33.7*/("""}"""),format.raw/*33.8*/("""

      """),format.raw/*35.7*/("""input[type=text] """),format.raw/*35.24*/("""{"""),format.raw/*35.25*/("""
        """),format.raw/*36.9*/("""width: 50%;
      """),format.raw/*37.7*/("""}"""),format.raw/*37.8*/("""


    """),format.raw/*40.5*/("""</style>

    <!-- Custom styles for this template -->
    <!--    <link href="signin.css" rel="stylesheet">-->
</head>
<body class="text-center container">

<main class="form-signin">
    <form>
        <img class="mb-4" alt="" width="72" height="57">
        <h1 class="h3 mb-3 fw-normal">Question?</h1>
        <label for="answer" class="visually-hidden">Answer:</label>
        <div class="row">
            <div class="col-md-12">
                <input type="text" id="answer" class="form-control" placeholder="Type answer here" required autofocus>
            </div>
        </div>
        <br>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
    </form>
</main>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
        integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous">
</script>
</body>
""")))}),format.raw/*66.2*/("""




"""),format.raw/*71.1*/("""<!--&lt;!&ndash; Optional theme &ndash;&gt;-->
<!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" -->
<!--      integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">-->

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
                  DATE: 2021-02-22T19:53:27.262067
                  SOURCE: /Users/Luke/polybody-macro-calc/app/views/template.scala.html
                  HASH: f007ee3fb8fec1f4a71e50252a775749bc75a5bb
                  MATRIX: 725->1|821->5|868->44|918->57|945->58|1761->846|1790->847|1826->856|1905->908|1933->909|1968->917|2016->937|2045->938|2081->947|2258->1097|2286->1098|2321->1106|2366->1123|2395->1124|2431->1133|2476->1151|2504->1152|2538->1159|3495->2086|3527->2091
                  LINES: 21->1|26->2|26->2|26->2|27->3|46->22|46->22|47->23|49->25|49->25|51->27|51->27|51->27|52->28|57->33|57->33|59->35|59->35|59->35|60->36|61->37|61->37|64->40|90->66|95->71
                  -- GENERATED --
              */
          