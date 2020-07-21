package com.mindtree.shoppingapp.service;

import com.mindtree.shoppingapp.entity.Cart;
import com.mindtree.shoppingapp.entity.User;
import com.mindtree.shoppingapp.exception.service.ShoppingKartServiceException;

/**
 * @author M1049006
 *
 */
public interface UserService {

	/**
	 * @param user
	 * @return User
	 */
	public User registerUser(User user);

	/**
	 * @param productId
	 * @param userId
	 * @return Boolean
	 */
	public Boolean addToCart(int productId, int userId);

	/**
	 * @param productName
	 * @param userId
	 * @return Boolean
	 */
	public Boolean removeSingleProduct(String productName, int userId) throws ShoppingKartServiceException;

	/**
	 * @param userId
	 * @return Boolean
	 */
	public Boolean removeAllCartItems(int userId);

	/**
	 * @param userId
	 * @param productName
	 * @param quantity
	 * @return Boolean
	 * @throws RuntimeException
	 */
	public Boolean updateCartItem(int userId, String productName, int quantity) throws ShoppingKartServiceException;

	/**
	 * @param userId
	 * @return Boolean
	 */
	public Cart viewCart(int userId);

}
