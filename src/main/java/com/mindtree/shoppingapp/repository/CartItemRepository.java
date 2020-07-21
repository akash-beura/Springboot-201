package com.mindtree.shoppingapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingapp.entity.CartItem;

/**
 * @author M1049006
 *
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "DELETE from CartItem c WHERE c.cart.cartId=:cartId AND c.itemName=:productName")
	public void deleteCartItem(@Param("cartId") int cartId, @Param("productName") String productName);

	@Transactional
	@Modifying
	@Query(value = "DELETE from CartItem c WHERE c.cart.cartId=:cartId")
	public void deleteAllCartItems(@Param("cartId") int cartId);

	@Query(value = "FROM CartItem c WHERE c.cart.cartId=:cartId AND c.itemName=:productName")
	public CartItem getParticularCartItem(@Param("cartId") int cartId, @Param("productName") String productName);

}
