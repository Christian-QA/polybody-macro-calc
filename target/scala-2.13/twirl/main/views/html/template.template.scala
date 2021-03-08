
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

object template extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[String,String,Option[String],Option[String],Option[Int],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, formType: String, param1: Option[String] = None, param2: Option[String] = None, param3: Option[Int] = None):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.125*/("""
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



      .bd-placeholder-img """),format.raw/*24.27*/("""{"""),format.raw/*24.28*/("""
        """),format.raw/*25.9*/("""font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      """),format.raw/*30.7*/("""}"""),format.raw/*30.8*/("""

      """),format.raw/*32.7*/("""input[type=text] """),format.raw/*32.24*/("""{"""),format.raw/*32.25*/("""
        """),format.raw/*33.9*/("""width: 50%;
      """),format.raw/*34.7*/("""}"""),format.raw/*34.8*/("""


    """),format.raw/*37.5*/("""</style>

    <!-- Custom styles for this template -->
    <!--    <link href="signin.css" rel="stylesheet">-->
</head>
<body class="text-center container">

<main class="form-signin">
    <form>
        <img class="mb-4" alt="" width="72" height="57">
        <h1 class="h3 mb-3 fw-normal">"""),_display_(/*47.40*/Html(title)),format.raw/*47.51*/("""</h1>
        <label for="answer" class="visually-hidden">Answer:</label>
        <div class="row">
            <div class="col-md-12 align-content-center">
                """),_display_(/*51.18*/formType/*51.26*/ match/*51.32*/ {/*52.21*/case "text" =>/*52.35*/ {_display_(Seq[Any](format.raw/*52.37*/("""
                        """),format.raw/*53.25*/("""<input type="text" id="answer" class="right" placeholder="Type answer here" required autofocus>
                    """)))}/*55.21*/case "radio" =>/*55.36*/ {_display_(Seq[Any](format.raw/*55.38*/("""
                """),format.raw/*56.17*/("""<fieldset>
                    <label for=""""),_display_(/*57.34*/Html(param1)),format.raw/*57.46*/("""">"""),_display_(/*57.49*/Html(param1)),format.raw/*57.61*/("""</label>
                        <input type="radio" id=""""),_display_(/*58.50*/Html(param1)),format.raw/*58.62*/("""" class="form-control" value=""""),_display_(/*58.93*/Html(param1)),format.raw/*58.105*/("""">
                    <label for=""""),_display_(/*59.34*/Html(param2)),format.raw/*59.46*/("""">"""),_display_(/*59.49*/Html(param2)),format.raw/*59.61*/("""</label>
                        <input type="radio" id=""""),_display_(/*60.50*/Html(param2)),format.raw/*60.62*/("""" class="form-control" value=""""),_display_(/*60.93*/Html(param2)),format.raw/*60.105*/("""">
                </fieldset>
                        """)))}/*63.21*/case _ =>/*63.30*/ {_display_(Seq[Any](format.raw/*63.32*/("""
                        """),format.raw/*64.25*/("""<h1>No formtype help pls pls pls I beg you pls</h1>
                    """)))}}),format.raw/*66.18*/("""
            """),format.raw/*67.13*/("""</div>
        </div>
        <br>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
    </form>
</main>

<footer>
    <p>
        <a href="https://www.w3schools.com/">| Age |</a>
        <a href="https://www.w3schools.com/">| Current Weight |</a>
        <a href="https://www.w3schools.com/">| Gender |</a>
        <a href="https://www.w3schools.com/">| Height |</a>
        <a href="https://www.w3schools.com/">| Target Weight |</a>
    </p>
</footer>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
        integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous">
</script>
</body>

""")))}),format.raw/*89.2*/("""




"""),format.raw/*94.1*/("""<!--&lt;!&ndash; Optional theme &ndash;&gt;-->
<!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" -->
<!--      integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">-->

"""))
      }
    }
  }

  def render(title:String,formType:String,param1:Option[String],param2:Option[String],param3:Option[Int]): play.twirl.api.HtmlFormat.Appendable = apply(title,formType,param1,param2,param3)

  def f:((String,String,Option[String],Option[String],Option[Int]) => play.twirl.api.HtmlFormat.Appendable) = (title,formType,param1,param2,param3) => apply(title,formType,param1,param2,param3)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-03-08T20:14:20.866
                  SOURCE: C:/Users/felix/source/repos/polybody-macro-calc/app/views/template.scala.html
                  HASH: 024ed257776cacc5c483ab004ee9d408c2a32546
                  MATRIX: 781->1|1000->124|1028->127|1075->166|1125->179|1153->181|2003->1003|2032->1004|2069->1014|2251->1169|2279->1170|2316->1180|2361->1197|2390->1198|2427->1208|2473->1227|2501->1228|2538->1238|2867->1540|2899->1551|3104->1729|3121->1737|3136->1743|3147->1767|3170->1781|3210->1783|3264->1809|3401->1949|3425->1964|3465->1966|3511->1984|3583->2029|3616->2041|3646->2044|3679->2056|3765->2115|3798->2127|3856->2158|3890->2170|3954->2207|3987->2219|4017->2222|4050->2234|4136->2293|4169->2305|4227->2336|4261->2348|4338->2428|4356->2437|4396->2439|4450->2465|4556->2558|4598->2572|5360->3304|5397->3314
                  LINES: 21->1|26->1|27->2|27->2|27->2|28->3|49->24|49->24|50->25|55->30|55->30|57->32|57->32|57->32|58->33|59->34|59->34|62->37|72->47|72->47|76->51|76->51|76->51|76->52|76->52|76->52|77->53|78->55|78->55|78->55|79->56|80->57|80->57|80->57|80->57|81->58|81->58|81->58|81->58|82->59|82->59|82->59|82->59|83->60|83->60|83->60|83->60|85->63|85->63|85->63|86->64|87->66|88->67|110->89|115->94
                  -- GENERATED --
              */
          