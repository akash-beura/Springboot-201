package com.mindtree.shoppingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingapp.dto.ApiResponse;
import com.mindtree.shoppingapp.entity.User;
import com.mindtree.shoppingapp.exception.ShoppingKartAppException;
import com.mindtree.shoppingapp.service.UserService;
import com.mindtree.shoppingapp.utility.ResponseUtility;

/**
 * @author M1049006
 * 
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * @param user
	 * @return API Response
	 */
	@PostMapping("/register") //works
	public ResponseEntity<ApiResponse> registerUser(@RequestBody User user) {
		return new ResponseEntity<ApiResponse>(ResponseUtility.responseBuilder(userService.registerUser(user),
				HttpStatus.CREATED, false, "User registered and cart assigned."), HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @param productId
	 * @return API Response
	 */
	@PostMapping("/{userId}/cart/{productId}") // works
	public ResponseEntity<ApiResponse> addToCart(@PathVariable int userId, @PathVariable int productId) {
		return new ResponseEntity<ApiResponse>(ResponseUtility.responseBuilder(userService.addToCart(productId, userId),
				HttpStatus.ACCEPTED, false, "Added to cart"), HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @param productName
	 * @return API Response
	 */
	@DeleteMapping("/{userId}/cart/v1") // works
	public ResponseEntity<ApiResponse> removeSingleProduct(@PathVariable int userId, @RequestParam String productName)
			throws ShoppingKartAppException {
		return new ResponseEntity<ApiResponse>(
				ResponseUtility.responseBuilder(userService.removeSingleProduct(productName, userId),
						HttpStatus.ACCEPTED, false, "Cart item removed"),
				HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @return API Response
	 */
	@DeleteMapping("/{userId}/cart/v2") // works
	public ResponseEntity<ApiResponse> emptyCart(@PathVariable int userId) {
		return new ResponseEntity<ApiResponse>(ResponseUtility.responseBuilder(userService.removeAllCartItems(userId),
				HttpStatus.ACCEPTED, false, "Removed all cart items."), HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @param productName
	 * @param quantity
	 * @return API Response
	 */
	@PutMapping("/{userId}/cart")
	public ResponseEntity<ApiResponse> updateCartItemQuantity(@PathVariable int userId,
			@RequestParam String productName, @RequestParam int quantity) throws ShoppingKartAppException {
		return new ResponseEntity<ApiResponse>(
				ResponseUtility.responseBuilder(userService.updateCartItem(userId, productName, quantity),
						HttpStatus.ACCEPTED, false, "CartUpdated"),
				HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @return API Response
	 */
	@GetMapping("/{userId}/cart")
	public ResponseEntity<ApiResponse> viewCart(@PathVariable int userId) {
		return new ResponseEntity<ApiResponse>(ResponseUtility.responseBuilder(userService.viewCart(userId),
				HttpStatus.FOUND, false, "Fetched all cart items."), HttpStatus.OK);
	}

}
