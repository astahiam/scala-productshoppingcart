package com.astahiam.scala.produk.domain

case class CustomerSearchParameters(customerName: Option[String] = None,
                                    customerAddress: Option[String] = None,
                                    customerPhone: Option[String] = None)