package com.mindtree.shoppingapp.exception.service;

public class NegativeQuantityException extends ShoppingKartServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5643821104701769950L;

	public NegativeQuantityException() {
		// TODO Auto-generated constructor stub
	}

	public NegativeQuantityException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NegativeQuantityException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NegativeQuantityException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
