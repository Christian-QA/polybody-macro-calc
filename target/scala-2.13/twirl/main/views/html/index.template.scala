
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

<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Title</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

  </head>

  <body>

<!--    <header>-->
<!--      <div class="page-header">-->
<!--        <h1 class="text-primary">THIS IS A HEADER</h1>-->
<!--      </div>-->
<!--    </header>-->
<!--    <h1>THIS IS A QUESTION</h1>-->
<!--    <input type="text">-->
<!--    <footter>-->
<!--      THIS IS A FOOTER-->
<!--    </footter>-->

      <div class="container my-container">
        <div class="row" style="height: 200px">
          <div class="col justify-content-end my-row">
            <header>
              <h1 class="text-center">THIS IS A HEADER</h1>
            </header>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h2 class="text-center">THIS IS A QUESTION</h2>
          </div>
        </div>
        <div class="row border border-dark d-flex justify-content-center">
          <div class="col">
            <input type="text">
          </div>
        </div>
        <div class="row">
          <div class="col">
            <footer class="text-center">
              THIS IS A FOOTER
            </footer>
          </div>
        </div>
      </div>

  <!-- Latest compiled and minified JavaScript -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
  </body>
</html>



















<!--<!DOCTYPE html>-->
<!--<html lang="en">-->

<!--<head>-->
<!--  <meta charset="UTF-8">-->
<!--  <meta name="viewport" content="width=device-width, initial-scale=1.0">-->
<!--  <meta http-equiv="X-UA-Compatible" content="ie=edge">-->
<!--  <title>Bootstrap 4 Introduction</title>-->
<!--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"-->
<!--        crossorigin="anonymous">-->
<!--  <style>-->
<!--        body """),format.raw/*159.18*/("""{"""),format.raw/*159.19*/("""-->
<!--            margin: 30px;-->
<!--        """),format.raw/*161.13*/("""}"""),format.raw/*161.14*/("""-->

<!--        .my-container """),format.raw/*163.27*/("""{"""),format.raw/*163.28*/("""-->
<!--            border: 1px solid green;-->
<!--        """),format.raw/*165.13*/("""}"""),format.raw/*165.14*/("""-->

<!--        .my-row """),format.raw/*167.21*/("""{"""),format.raw/*167.22*/("""-->
<!--            border: 3px solid red;-->
<!--            height: 300px;-->
<!--        """),format.raw/*170.13*/("""}"""),format.raw/*170.14*/("""-->

<!--        .my-col """),format.raw/*172.21*/("""{"""),format.raw/*172.22*/("""-->
<!--            border: 3px dotted blue;-->
<!--        """),format.raw/*174.13*/("""}"""),format.raw/*174.14*/("""-->
<!--    </style>-->
<!--</head>-->

<!--<body>-->
<!--<div class="container my-container">-->
<!--  <div class="row my-row">-->
<!--    <div class="col-md-4 col-sm-6 my-col">-->
<!--      Row 1 Col 1-->
<!--    </div>-->
<!--    <div class="col-md-8 col-sm-6 my-col">-->
<!--      Row 1 Col 2-->
<!--    </div>-->
<!--  </div>-->
<!--  <div class="row justify-content-between align-items-stretch my-row">-->
<!--    <div class="col-4 my-col order-md-12">-->
<!--      Row 2 Col 1-->
<!--    </div>-->
<!--    <div class="col-4 offset-md-2 my-col align-self-start order-md-2">-->
<!--      Row 2 Col 2-->
<!--    </div>-->
<!--  </div>-->
<!--</div>-->
<!--</body>-->

<!--</html>-->
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
                  DATE: 2021-02-16T15:43:14.790448
                  SOURCE: /Users/Luke/polybody-macro-calc/app/views/index.scala.html
                  HASH: cf377f10a905e76dc560851e926532fa8f11259b
                  MATRIX: 811->0|841->5|862->7|897->17|920->32|959->34|993->43|1040->82|1090->95|2695->1672|2724->1679|2857->1785|2886->1792|3025->1904|3053->1911|3196->2027|3224->2034|3345->2127|3374->2134|5199->3932|5214->3938|5258->3961|5389->4065|5425->4080|5518->4143|5557->4152|7918->6484|7948->6485|8026->6534|8056->6535|8116->6566|8146->6567|8235->6627|8265->6628|8319->6653|8349->6654|8470->6746|8500->6747|8554->6772|8584->6773|8673->6833|8703->6834
                  LINES: 26->1|26->1|26->1|28->3|28->3|28->3|29->4|29->4|29->4|55->30|55->30|56->31|56->31|57->32|57->32|58->33|58->33|58->33|58->33|88->63|88->63|88->63|91->66|91->66|95->70|96->71|184->159|184->159|186->161|186->161|188->163|188->163|190->165|190->165|192->167|192->167|195->170|195->170|197->172|197->172|199->174|199->174
                  -- GENERATED --
              */
          