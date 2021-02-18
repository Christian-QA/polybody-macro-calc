
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

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<!--"""),_display_(/*1.6*/()),format.raw/*1.8*/("""-->

<!--"""),_display_(/*3.6*/main("Welcome")/*3.21*/ {_display_(Seq[Any](format.raw/*3.23*/("""-->
<!--"""),_display_(/*4.6*/defining(play.core.PlayVersion.current)/*4.45*/ { version =>_display_(Seq[Any](format.raw/*4.58*/("""-->

<!--<section id="content">-->
<!--  <div class="wrapper doc">-->
<!--    <article>-->
<!--      <h2>Welcome to the Hello World Tutorial!</h2>-->
<!--      <p>This tutorial introduces Play Framework, describes how Play web applications work, and walks you through steps-->
<!--        to create page that displays a Hello World greeting.</p>-->
<!--      <p>If you loaded this page from the web server running on <code>localhost:9000</code>, congratulations! You have-->
<!--        successfully built and run a Play application. If not, you likely opened the source <code>index.scala.html</code>-->
<!--        file. Please follow the directions in the <code>README.md</code> file in the top-level project directory to run-->
<!--        the tutorial.</p>-->

<!--      <h3 id="introduction">Introduction to Play</h3>-->
<!--      <p>As illustrated below, Play is a full-stack framework with all of the components you need to build a Web-->
<!--        Application or a REST service, including:</p>-->
<!--      <ul>-->
<!--        <li>An integrated HTTP server</li>-->
<!--        <li>Form handling</li>-->
<!--        <li>Cross-Site Request Forgery (CSRF) protection</li>-->
<!--        <li>A powerful routing mechanism</li>-->
<!--        <li>I18n support, and more.</li>-->
<!--      </ul>-->

<!--      <img src="assets/images/play-stack.png" alt="Play Stack" class="small-5 medium-4 large-3" />-->

<!--      <p>Play integrates with many object relational mapping (ORM) layers. It has out-of-the-box support for <a href="https://www.playframework.com/documentation/"""),_display_(/*30.168*/version),format.raw/*30.175*/("""/Anorm"-->
<!--          target="_blank">Anorm</a>, <a href="https://www.playframework.com/documentation/"""),_display_(/*31.96*/version),format.raw/*31.103*/("""/JavaEbean" target="_blank">JavaEbean</a>,-->
<!--        <a href="https://www.playframework.com/documentation/"""),_display_(/*32.67*/version),format.raw/*32.74*/("""/PlaySlick" target="_blank">PlaySlick</a>, and-->
<!--        <a href="https://www.playframework.com/documentation/"""),_display_(/*33.67*/version),format.raw/*33.74*/("""/JavaJPA" target="_blank">JPA</a>. See <a href="https://www.playframework.com/documentation/"""),_display_(/*33.167*/version),format.raw/*33.174*/("""/JavaDatabase"-->
<!--          target="_blank">Accessing an SQL Database</a> for more information. Many customers use NoSQL, other ORMs or-->
<!--        even access data from a REST service.</p>-->


<!--      <p>Play APIs are available in both Scala and Java. The Framework uses <a href="https://akka.io" target="_blank">Akka</a>-->
<!--        and <a href="https://doc.akka.io/docs/akka-http/current/index.html" target="_blank">Akka HTTP</a> under the-->
<!--        hood. This endows Play applications with a stateless, non-blocking, event-driven architecture that provides-->
<!--        horizontal and vertical scalability and uses resources more efficiently.</p>-->

<!--      <p>Here are just a few of the reasons developers love using Play Framework:</p>-->
<!--      <ul>-->
<!--        <li>Its Model-View-Controller (MVC) architecture is familiar and easy to learn.</li>-->
<!--        <li>Direct support of common web development tasks and hot reloading saves precious development time.</li>-->
<!--        <li>A large active community promotes knowledge sharing.</li>-->
<!--        <li>Use of <a href="https://github.com/playframework/twirl">Twirl templates</a> to render pages. The Twirl-->
<!--          template language is:-->
<!--          <ol>-->
<!--            <li>Easy to learn</li>-->
<!--            <li>Requires no special editor</li>-->
<!--            <li>Provides type safety</li>-->
<!--            <li>Is compiled so that errors display in the browser</li>-->
<!--          </ol>-->
<!--        </li>-->
<!--      </ul>-->

<!--      <p>To learn more about Play's benefits, visit the <a href="https://www.playframework.com" target="_blank">Play-->
<!--          website</a>.</p>-->

<!--      <h3 id="next-steps">Next steps</h3>-->
<!--      <p>Now, let's <a href=""""),_display_(/*63.35*/routes/*63.41*/.HomeController.explore),format.raw/*63.64*/("""">explore the tutorial Play application</a>.</p>-->
<!--    </article>-->
<!--    <aside>-->
<!--      """),_display_(/*66.12*/commonSidebar()),format.raw/*66.27*/("""-->
<!--    </aside>-->
<!--  </div>-->
<!--</section>-->
<!--""")))}),format.raw/*70.6*/("""-->
<!--""")))}),format.raw/*71.6*/("""-->



<!doctype html>
<html lang="en">
<head>
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

    .container """),format.raw/*96.16*/("""{"""),format.raw/*96.17*/("""
        """),format.raw/*97.9*/("""display: flex;
        justify-content: center;
    """),format.raw/*99.5*/("""}"""),format.raw/*99.6*/("""

      """),format.raw/*101.7*/(""".bd-placeholder-img """),format.raw/*101.27*/("""{"""),format.raw/*101.28*/("""
        """),format.raw/*102.9*/("""font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      """),format.raw/*107.7*/("""}"""),format.raw/*107.8*/("""

      """),format.raw/*109.7*/("""input[type=text] """),format.raw/*109.24*/("""{"""),format.raw/*109.25*/("""
        """),format.raw/*110.9*/("""width: 50%;
      """),format.raw/*111.7*/("""}"""),format.raw/*111.8*/("""


    """),format.raw/*114.5*/("""</style>

    <!-- Custom styles for this template -->
<!--    <link href="signin.css" rel="stylesheet">-->
</head>

    <body class="text-center container">

        <main class="form-signin">
            <form>
                <img class="mb-4" alt="" width="72" height="57">
                <h1 class="h3 mb-3 fw-normal">Question?</h1>
                <label for="inputEmail" class="visually-hidden">Answer:</label>
                <input type="text" id="inputEmail" class="form-control align-items-end" placeholder="Type answer here" required autofocus>
<!--                <label for="inputPassword" class="visually-hidden">Password</label>-->
<!--                <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>-->
                <div class="checkbox mb-3">
<!--                    <label>-->
<!--                        <input type="checkbox" value="remember-me"> Remember me-->
<!--                    </label>-->
                </div>
                <br>
                <button class="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
<!--                <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>-->
            </form>
        </main>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
               integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>

    </body>
</html>










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
                  DATE: 2021-02-18T11:37:00.285287
                  SOURCE: /Users/Luke/polybody-macro-calc/app/views/index.scala.html
                  HASH: 6ef7a138a595333adef1f0c314198aa2168ec26f
                  MATRIX: 811->0|841->5|862->7|897->17|920->32|959->34|993->43|1040->82|1090->95|2695->1672|2724->1679|2857->1785|2886->1792|3025->1904|3053->1911|3196->2027|3224->2034|3345->2127|3374->2134|5199->3932|5214->3938|5258->3961|5389->4065|5425->4080|5518->4143|5557->4152|6413->4980|6442->4981|6478->4990|6557->5042|6585->5043|6621->5051|6670->5071|6700->5072|6737->5081|6915->5231|6944->5232|6980->5240|7026->5257|7056->5258|7093->5267|7139->5285|7168->5286|7203->5293
                  LINES: 26->1|26->1|26->1|28->3|28->3|28->3|29->4|29->4|29->4|55->30|55->30|56->31|56->31|57->32|57->32|58->33|58->33|58->33|58->33|88->63|88->63|88->63|91->66|91->66|95->70|96->71|121->96|121->96|122->97|124->99|124->99|126->101|126->101|126->101|127->102|132->107|132->107|134->109|134->109|134->109|135->110|136->111|136->111|139->114
                  -- GENERATED --
              */
          