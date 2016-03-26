package com.astahiam.scala.produk

import org.scalatra._

class produkScalatraServlet extends ScalaStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

}
