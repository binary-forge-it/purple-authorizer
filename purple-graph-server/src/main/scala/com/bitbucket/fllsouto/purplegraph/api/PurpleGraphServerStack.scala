package com.bitbucket.fllsouto.purplegraph.api

import org.scalatra._
import scalate.ScalateSupport
import org.fusesource.scalate.{ TemplateEngine, Binding }
import org.fusesource.scalate.layout.DefaultLayoutStrategy
import javax.servlet.http.HttpServletRequest
import collection.mutable

trait PurpleGraphServerStack extends ScalatraServlet with ScalateSupport {

	get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello!!!!!!! to Scalate</a>.
      </body>
    </html>
  }

  notFound {
    contentType = null
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound()
  }

}
