package com.mindtree.shoppingapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingapp.entity.Product;
import com.mindtree.shoppingapp.repository.ProductRepository;
import com.mindtree.shoppingapp.service.ShoppingService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class, isolation = Isolation.READ_COMMITTED)
public class ShoppingServiceImpl implements ShoppingService {

	private ProductRepository productRepository;

	@Autowired
	public ShoppingServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public List<Product> fetchProducts() {
		return productRepository.findAll();
	}

	public Product findProductByName(String productName) {
		Optional<Product> product = productRepository.findByProductName(productName);
		if (product.isPresent()) {
			return product.get();
		} else {
			return null;
		}
	}

}
