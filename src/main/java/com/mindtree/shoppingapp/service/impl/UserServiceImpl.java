package com.mindtree.shoppingapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingapp.entity.Cart;
import com.mindtree.shoppingapp.entity.CartItem;
import com.mindtree.shoppingapp.entity.Product;
import com.mindtree.shoppingapp.entity.User;
import com.mindtree.shoppingapp.exception.service.NegativeQuantityException;
import com.mindtree.shoppingapp.exception.service.ShoppingKartServiceException;
import com.mindtree.shoppingapp.repository.CartItemRepository;
import com.mindtree.shoppingapp.repository.CartRepository;
import com.mindtree.shoppingapp.repository.ProductRepository;
import com.mindtree.shoppingapp.repository.UserRepository;
import com.mindtree.shoppingapp.service.UserService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class, isolation = Isolation.READ_COMMITTED)
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserRepository userRepository;
	private CartRepository cartRepository;
	private ProductRepository productRepository;
	private CartItemRepository itemRepository;
	private Environment env;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, CartRepository cartRepository,
			ProductRepository productRepository, CartItemRepository itemRepository, Environment env) {
		super();
		this.userRepository = userRepository;
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
		this.itemRepository = itemRepository;
		this.env = env;
	}

	public User registerUser(User user) {
		Cart cart = new Cart();
		user.setCart(cart);
		cart.setUser(user);
		return userRepository.save(user);
	}

	public Boolean addToCart(int productId, int userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			return false;
		} else {
			Product product = productRepository.findById(productId).orElse(null);
			if (product != null) {
				Cart cart = user.getCart();
				Boolean itemPresent = false;
				for (CartItem cartItem : cart.getCartItems()) {
					if (cartItem.getItemName().equals(product.getProductName())
							&& cartItem.getItemCost() == product.getPrice()) {
						cartItem.setItemQuantity(cartItem.getItemQuantity() + 1);
						cartItem.setTotalItemPrice(cartItem.getItemCost() * cartItem.getItemQuantity());
						cart.setTotalCost(cart.getTotalCost() + product.getPrice());
						itemPresent = true;
					}
				}
				if (itemPresent == false) {
					CartItem cartItem = new CartItem();
					cartItem.setCart(cart);
					cartItem.setItemCost(product.getPrice());
					cartItem.setItemQuantity(1);
					cartItem.setItemName(product.getProductName());
					cartItem.setTotalItemPrice(cartItem.getItemCost());
					cart.getCartItems().add(cartItem);
					cart.setTotalCost(cart.getTotalCost() + cartItem.getItemCost());
				}
				cartRepository.save(cart);
				return true;
			} else {
				return false;
			}
		}
	}

	public Boolean removeSingleProduct(String productName, int userId) throws ShoppingKartServiceException {
		try {
			Cart cart = cartRepository.findByUserId(userId);
			itemRepository.deleteCartItem(cart.getCartId(), productName);
			return true;
		} catch (Exception e) {
			throw new ShoppingKartServiceException(e.getMessage(), e);
		}

	}

	public Boolean removeAllCartItems(int userId) {
		Cart cart = cartRepository.findByUserId(userId);
		itemRepository.deleteAllCartItems(cart.getCartId());
		cart.setTotalCost(0);
		cartRepository.save(cart);
		return true;
	}

	public Boolean updateCartItem(int userId, String productName, int quantity) throws ShoppingKartServiceException {
		if (quantity < 0) {
			logger.debug("Negative quantity exception occured at updateCartItem in UserServiceImpl");
			throw new NegativeQuantityException(env.getProperty("invalid_quantity"));
		} else {
			Cart cart = cartRepository.findByUserId(userId);
			CartItem cartItem = itemRepository.getParticularCartItem(cart.getCartId(), productName);
			if (quantity == 0) {
				cart.setTotalCost(cart.getTotalCost() - cartItem.getTotalItemPrice());
				itemRepository.deleteCartItem(cart.getCartId(), productName);
			} else {
				cart.setTotalCost(cart.getTotalCost() - cartItem.getTotalItemPrice());
				float itemPerPiece = cartItem.getTotalItemPrice() / cartItem.getItemQuantity();
				cartItem.setItemQuantity(quantity);
				cartItem.setTotalItemPrice(itemPerPiece * quantity);
				cart.setTotalCost(cart.getTotalCost() + cartItem.getTotalItemPrice());
			}
			cartRepository.save(cart);
			return true;
		}
	}

	public Cart viewCart(int userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user != null)
			return user.getCart();
		else
			return null;
	}

}
