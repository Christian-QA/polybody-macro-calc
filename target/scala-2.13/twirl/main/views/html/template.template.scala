
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

object template extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[String,String,Option[String],Option[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, formType: String, param1: Option[String], param2: Option[String]):play.twirl.api.HtmlFormat.Appendable = {
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
        <h1 class="h3 mb-3 fw-normal">"""),_display_(/*50.40*/Html(title)),format.raw/*50.51*/("""</h1>
        <label for="answer" class="visually-hidden">Answer:</label>
        <div class="row">
            <div class="col-md-12">
                """),_display_(/*54.18*/formType/*54.26*/ match/*54.32*/ {/*55.21*/case "text" =>/*55.35*/ {_display_(Seq[Any](format.raw/*55.37*/("""
                        """),format.raw/*56.25*/("""<input type="text" id="answer" class="form-control" placeholder="Type answer here" required autofocus>
                    """)))}/*58.21*/case "radio" =>/*58.36*/ {_display_(Seq[Any](format.raw/*58.38*/("""
                """),format.raw/*59.17*/("""<label for=""""),_display_(/*59.30*/Html(param1)),format.raw/*59.42*/("""">"""),_display_(/*59.45*/Html(param1)),format.raw/*59.57*/("""</label>
                        <input type="radio" id=""""),_display_(/*60.50*/Html(param1)),format.raw/*60.62*/("""" class="form-control" value=""""),_display_(/*60.93*/Html(param1)),format.raw/*60.105*/("""">
                <label for=""""),_display_(/*61.30*/Html(param2)),format.raw/*61.42*/("""">"""),_display_(/*61.45*/Html(param2)),format.raw/*61.57*/("""</label>
                        <input type="radio" id=""""),_display_(/*62.50*/Html(param2)),format.raw/*62.62*/("""" class="form-control" value=""""),_display_(/*62.93*/Html(param2)),format.raw/*62.105*/("""">
                    """)))}/*64.21*/case _ =>/*64.30*/ {_display_(Seq[Any](format.raw/*64.32*/("""
                        """),format.raw/*65.25*/("""<h1>No formtype help pls pls pls I beg you pls</h1>
                    """)))}}),format.raw/*67.18*/("""
            """),format.raw/*68.13*/("""</div>
        </div>
        <br>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
    </form>
</main>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
        integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous">
</script>
</body>
""")))}),format.raw/*79.2*/("""




"""),format.raw/*84.1*/("""<!--&lt;!&ndash; Optional theme &ndash;&gt;-->
<!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" -->
<!--      integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">-->

"""))
      }
    }
  }

  def render(title:String,formType:String,param1:Option[String],param2:Option[String]): play.twirl.api.HtmlFormat.Appendable = apply(title,formType,param1,param2)

  def f:((String,String,Option[String],Option[String]) => play.twirl.api.HtmlFormat.Appendable) = (title,formType,param1,param2) => apply(title,formType,param1,param2)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-02-22T21:54:27.282
                  SOURCE: /Users/christianredfern/Documents/Projects/HMRC/Code/Personal/polybody-macro-calc/app/views/template.scala.html
                  HASH: 6fd8ef714c532211b83314259c7bb4ef09c80eea
                  MATRIX: 769->1|944->84|991->123|1041->136|1068->137|1884->925|1913->926|1949->935|2028->987|2056->988|2091->996|2139->1016|2168->1017|2204->1026|2381->1176|2409->1177|2444->1185|2489->1202|2518->1203|2554->1212|2599->1230|2627->1231|2661->1238|2980->1530|3012->1541|3192->1694|3209->1702|3224->1708|3235->1731|3258->1745|3298->1747|3351->1772|3494->1917|3518->1932|3558->1934|3603->1951|3643->1964|3676->1976|3706->1979|3739->1991|3824->2049|3857->2061|3915->2092|3949->2104|4008->2136|4041->2148|4071->2151|4104->2163|4189->2221|4222->2233|4280->2264|4314->2276|4357->2321|4375->2330|4415->2332|4468->2357|4573->2448|4614->2461|5004->2821|5036->2826
                  LINES: 21->1|26->2|26->2|26->2|27->3|46->22|46->22|47->23|49->25|49->25|51->27|51->27|51->27|52->28|57->33|57->33|59->35|59->35|59->35|60->36|61->37|61->37|64->40|74->50|74->50|78->54|78->54|78->54|78->55|78->55|78->55|79->56|80->58|80->58|80->58|81->59|81->59|81->59|81->59|81->59|82->60|82->60|82->60|82->60|83->61|83->61|83->61|83->61|84->62|84->62|84->62|84->62|85->64|85->64|85->64|86->65|87->67|88->68|99->79|104->84
                  -- GENERATED --
              */
          