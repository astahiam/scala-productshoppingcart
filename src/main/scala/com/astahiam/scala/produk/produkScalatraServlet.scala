package com.astahiam.scala.produk

import org.scalatra._
import scalate.ScalateSupport
import net.liftweb.json.compact
import net.liftweb.json.render
import net.liftweb.json.JsonDSL._
import net.liftweb.json.Serialization.{read, write}
import net.liftweb.json.Serialization
import net.liftweb.json.NoTypeHints
import org.scalatra.Ok
import org.scalatra.NotFound
import com.astahiam.scala.produk.repository.ProductRepository
import org.scalatra.Created
import scala.collection.immutable.Map
import com.astahiam.scala.produk.model.Product

class produkScalatraServlet extends ScalaStack {
	
  // simple logger
  //val logger = Logger(classOf[produkScalatraServlet]);

  // repo stores our items
  val productRepo = new ProductRepository;
  
  // implicit value for json serialization format
  implicit val formats = Serialization.formats(NoTypeHints);

	
	get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }
	
	get("/product/:productID") {
    // set the result content type
    contentType = "application/product+json"
      
    // convert response to json and return as OK
    productRepo.get(params("productID").toInt) match {
      case Some(x) => Ok(write(x));
      case None => NotFound("Product with id " + params("productID") + " not found");
    }
  }
	
  notFound {
    // remove content type in case it was set through an action
    contentType = null 
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound() 
  }

}
