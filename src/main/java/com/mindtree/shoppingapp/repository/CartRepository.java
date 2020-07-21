package com.mindtree.shoppingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingapp.entity.Cart;

/**
 * @author M1049006
 *
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	@Query("SELECT c from Cart c where c.user.userId=:userId")
	public Cart findByUserId(@Param("userId")int userId);

}
