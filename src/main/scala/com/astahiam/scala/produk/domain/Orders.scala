package com.astahiam.scala.produk.domain

import scala.slick.driver.MySQLDriver.simple._

case class Orders(OrderID : Integer,ProductID: Integer, CustomerID: Integer, OrderDate: Option[java.util.Date], Quantity : Integer)

/**
 * Mapped product table object.
 */
object Products extends Table[Product]("Product") {

  def OrderID = column[integer]("OrderID", O.PrimaryKey, O.AutoInc)

  def ProductID = column[integer]("ProductID")

  def CustomerID = column[integer]("CustomerID")

  def OrderDate = column[java.util.Date]("OrderDate")
  
  def Quantity = column[Integer]("Quantity")

  
  val findById = for {
    ProductID <- Parameters[Integer]
    c <- this if c.ProductID is ProductID
  } yield c
}