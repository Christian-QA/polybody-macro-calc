
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

object template extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, formType: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.35*/("""
"""),_display_(/*2.2*/defining(play.core.PlayVersion.current)/*2.41*/ { version =>_display_(Seq[Any](format.raw/*2.54*/("""
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
        <h1 class="h3 mb-3 fw-normal">"""),_display_(/*50.40*/Html(title)),format.raw/*50.51*/("""</h1>
        <label for="answer" class="visually-hidden">Answer:</label>
        <div class="row">
            <div class="col-md-12">
                """),_display_(/*54.18*/if(formType == "text")/*54.40*/{_display_(Seq[Any](format.raw/*54.41*/("""
                    """),format.raw/*55.21*/("""<input type="text" id="answer" class="form-control" placeholder="Type answer here" required autofocus>
                """)))}),format.raw/*56.18*/("""
                """),_display_(/*57.18*/if(formType == "radio")/*57.41*/{_display_(Seq[Any](format.raw/*57.42*/("""
                    """),format.raw/*58.21*/("""<input type="radio" id="radio1" class="form-control">
                    <input type="radio" id="radio2" class="form-control">
                """)))}),format.raw/*60.18*/("""
            """),format.raw/*61.13*/("""</div>
        </div>
        <br>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
    </form>
</main>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
        integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous">
</script>
</body>
""")))}),format.raw/*72.2*/("""




"""),format.raw/*77.1*/("""<!--&lt;!&ndash; Optional theme &ndash;&gt;-->
<!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" -->
<!--      integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">-->

"""))
      }
    }
  }

  def render(title:String,formType:String): play.twirl.api.HtmlFormat.Appendable = apply(title,formType)

  def f:((String,String) => play.twirl.api.HtmlFormat.Appendable) = (title,formType) => apply(title,formType)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-02-22T21:38:59.848
                  SOURCE: C:/Users/felix/source/repos/polybody-macro-calc/app/views/template.scala.html
                  HASH: fbbbc284c56205ee87e6ba7b88408b5df5fb23a7
                  MATRIX: 739->1|867->34|895->37|942->76|992->89|1020->91|1855->898|1884->899|1921->909|2002->963|2030->964|2067->974|2115->994|2144->995|2181->1005|2363->1160|2391->1161|2428->1171|2473->1188|2502->1189|2539->1199|2585->1218|2613->1219|2650->1229|2979->1531|3011->1542|3195->1699|3226->1721|3265->1722|3315->1744|3467->1865|3513->1884|3545->1907|3584->1908|3634->1930|3812->2077|3854->2091|4255->2462|4292->2472
                  LINES: 21->1|26->1|27->2|27->2|27->2|28->3|47->22|47->22|48->23|50->25|50->25|52->27|52->27|52->27|53->28|58->33|58->33|60->35|60->35|60->35|61->36|62->37|62->37|65->40|75->50|75->50|79->54|79->54|79->54|80->55|81->56|82->57|82->57|82->57|83->58|85->60|86->61|97->72|102->77
                  -- GENERATED --
              */
          