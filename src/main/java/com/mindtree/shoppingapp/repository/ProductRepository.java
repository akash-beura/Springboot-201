package com.mindtree.shoppingapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingapp.entity.Product;

/**
 * @author M1049006
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


	Optional<Product> findByProductName(String productName);

}
