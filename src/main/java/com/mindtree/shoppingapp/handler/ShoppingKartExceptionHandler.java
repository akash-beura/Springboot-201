package com.mindtree.shoppingapp.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.shoppingapp.dto.ApiResponse;
import com.mindtree.shoppingapp.exception.ShoppingKartAppException;
import com.mindtree.shoppingapp.utility.ResponseUtility;

@RestControllerAdvice
public class ShoppingKartExceptionHandler {

	@ExceptionHandler(ShoppingKartAppException.class)
	public ResponseEntity<ApiResponse> handleShoppingKartAppException(ShoppingKartAppException ex) {
		return new ResponseEntity<ApiResponse>(
				ResponseUtility.responseBuilder(null, HttpStatus.INTERNAL_SERVER_ERROR, true, ex.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
