
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
                """),format.raw/*59.17*/("""<fieldset>
                    <label for=""""),_display_(/*60.34*/Html(param1)),format.raw/*60.46*/("""">"""),_display_(/*60.49*/Html(param1)),format.raw/*60.61*/("""</label>
                        <input type="radio" id=""""),_display_(/*61.50*/Html(param1)),format.raw/*61.62*/("""" class="form-control" value=""""),_display_(/*61.93*/Html(param1)),format.raw/*61.105*/("""">
                    <label for=""""),_display_(/*62.34*/Html(param2)),format.raw/*62.46*/("""">"""),_display_(/*62.49*/Html(param2)),format.raw/*62.61*/("""</label>
                        <input type="radio" id=""""),_display_(/*63.50*/Html(param2)),format.raw/*63.62*/("""" class="form-control" value=""""),_display_(/*63.93*/Html(param2)),format.raw/*63.105*/("""">
                </fieldset>
                        """)))}/*66.21*/case _ =>/*66.30*/ {_display_(Seq[Any](format.raw/*66.32*/("""
                        """),format.raw/*67.25*/("""<h1>No formtype help pls pls pls I beg you pls</h1>
                    """)))}}),format.raw/*69.18*/("""
            """),format.raw/*70.13*/("""</div>
        </div>
        <br>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
    </form>
</main>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
        integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous">
</script>
</body>
""")))}),format.raw/*81.2*/("""




"""),format.raw/*86.1*/("""<!--&lt;!&ndash; Optional theme &ndash;&gt;-->
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
                  DATE: 2021-03-01T20:29:04.124
                  SOURCE: C:/Users/felix/source/repos/polybody-macro-calc/app/views/template.scala.html
                  HASH: a8ce349c217942cf2b30cb90494eebbc64f04131
                  MATRIX: 781->1|1000->124|1028->127|1075->166|1125->179|1153->181|1988->988|2017->989|2054->999|2135->1053|2163->1054|2200->1064|2248->1084|2277->1085|2314->1095|2496->1250|2524->1251|2561->1261|2606->1278|2635->1279|2672->1289|2718->1308|2746->1309|2783->1319|3112->1621|3144->1632|3328->1789|3345->1797|3360->1803|3371->1827|3394->1841|3434->1843|3488->1869|3632->2016|3656->2031|3696->2033|3742->2051|3814->2096|3847->2108|3877->2111|3910->2123|3996->2182|4029->2194|4087->2225|4121->2237|4185->2274|4218->2286|4248->2289|4281->2301|4367->2360|4400->2372|4458->2403|4492->2415|4569->2495|4587->2504|4627->2506|4681->2532|4787->2625|4829->2639|5230->3010|5267->3020
                  LINES: 21->1|26->1|27->2|27->2|27->2|28->3|47->22|47->22|48->23|50->25|50->25|52->27|52->27|52->27|53->28|58->33|58->33|60->35|60->35|60->35|61->36|62->37|62->37|65->40|75->50|75->50|79->54|79->54|79->54|79->55|79->55|79->55|80->56|81->58|81->58|81->58|82->59|83->60|83->60|83->60|83->60|84->61|84->61|84->61|84->61|85->62|85->62|85->62|85->62|86->63|86->63|86->63|86->63|88->66|88->66|88->66|89->67|90->69|91->70|102->81|107->86
                  -- GENERATED --
              */
          