package com.mindtree.shoppingapp.service;

import java.util.List;

import com.mindtree.shoppingapp.entity.Product;

/**
 * @author M1049006
 *
 */
public interface ShoppingService {

	/**
	 * @return List<Product>
	 */
	public List<Product> fetchProducts();

	/**
	 * @param productName
	 * @return Product
	 */
	public Product findProductByName(String productName);
}
