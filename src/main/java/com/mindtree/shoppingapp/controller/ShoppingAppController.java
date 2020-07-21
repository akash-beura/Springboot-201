package com.mindtree.shoppingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingapp.dto.ApiResponse;
import com.mindtree.shoppingapp.service.ShoppingService;
import com.mindtree.shoppingapp.utility.ResponseUtility;

/**
 * @author M1049006
 *
 */
@RestController
@RequestMapping("/api/shop")
public class ShoppingAppController {

	@Autowired
	private ShoppingService shoppingService;

	/**
	 * @return API Response
	 */
	@GetMapping("/products")
	public ResponseEntity<ApiResponse> fetchAllProducts() {
		return new ResponseEntity<ApiResponse>(ResponseUtility.responseBuilder(shoppingService.fetchProducts(),
				HttpStatus.FOUND, false, "All products fetched"), HttpStatus.OK);
	}

	/**
	 * @param productName
	 * @return API Response
	 */
	@GetMapping("/product")
	public ResponseEntity<ApiResponse> fetchUniqueProduct(@RequestParam String productName) {
		return new ResponseEntity<ApiResponse>(
				ResponseUtility.responseBuilder(shoppingService.findProductByName(productName), HttpStatus.FOUND, false,
						"Product fetched."),
				HttpStatus.OK);
	}

}
