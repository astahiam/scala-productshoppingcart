package com.astahiam.scala.produk.domain

import scala.slick.driver.MySQLDriver.simple._

case class Customer(CustomerID: Integer, CustomerName: String, CustomerAddress: String, CustomerPhone: String)

/**
 * Mapped customers table object.
 */
object Customers extends Table[Customer]("Customer") {

  def CustomerID = column[integer]("CustomerID", O.PrimaryKey, O.AutoInc)

  def CustomerName = column[String]("CustomerName")

  def CustomerAddress = column[String]("CustomerAddress")

  def CustomerAddress = column[String]("CustomerAddress")

  def * = id.? ~ customerName ~ customerAddress ~ customerPhone.? <>(Customer, Customer.unapply _)

  val findById = for {
    CustomerID <- Parameters[Integer]
    c <- this if c.CustomerID is CustomerID
  } yield c
}