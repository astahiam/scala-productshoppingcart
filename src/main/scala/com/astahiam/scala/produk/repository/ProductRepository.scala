import org.scalaquery.session.Database
 
trait RepositoryBase 
{
  val db = Database.forURL("jdbc:mysql://localhost:3306/productsc?user=root&password=", driver = "org.mysql.Driver")
}

package com.astahiam.scala.produk.repository

import com.astahiam.scala.produk.domain.Product
import org.scalaquery.session._
import org.scalaquery.ql.basic.{BasicTable => Table}
import org.scalaquery.ql.TypeMapper._
import org.scalaquery.ql._
import org.scalaquery.ql.extended.PostgresDriver.Implicit._
import org.scalaquery.session.Database.threadLocalSession

class ProductRepository extends RepositoryBase{
  
object ProductMapping extends Table[(Integer, String, Double, String, Double)]{

  def ProductID = column[Integer]("ProductID", O.PrimaryKey, O.AutoInc)

  def ProductName = column[String]("ProductName")

  def Weight = column[Double]("Weight")

  def Color = column[String]("Color")
  
  def Price = column[Double]("Price")
  
  val findById = for {
    ProductID <- Parameters[Integer]
    c <- this if c.ProductID is ProductID
  } yield c
}
	
	  def get(ProductID: Integer) : Option[Product] = {
      var result:Option[Product] = None;
 
      db withSession {
          // define the query and what we want as result
    	  val query = for (u <-ProductMapping if u.ProductID === ProductID) yield u.ProductID ~ u.ProductName ~ u.Weight ~ u.Color ~ u.Price
 
    	  // map the results to a Bid object
    	  val inter = query mapResult {
    	    case(ProductID,ProductName,Weight,Color,Price) => Option(new Product(ProductID,ProductName, Weight, Color, Price));
    	  }
 
    	  // check if there is one in the list and return it, or None otherwise
    	  result = inter.list match {
    	    case _ :: tail => inter.first
    	    case Nil => None
    	  }
      }
 
      // return the found product
      result
    }
}