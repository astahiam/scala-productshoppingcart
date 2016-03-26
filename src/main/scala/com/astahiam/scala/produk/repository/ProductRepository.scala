package com.astahiam.scala.produk.repository

import com.astahiam.scala.produk.model.Product

class ProductRepository{
	def get(productID: Number) : Option[Product] = {
	productID.intValue() match {
			case 123 => {
				val product = new Product(
								123,
								"Test Product",
								1.0,
								"White",
								1000000
				);
				Option(product);
			};
			case _ => Option(null);
		}
	}
	def delete(product: Product) = println("Deleting product :" + product)
}