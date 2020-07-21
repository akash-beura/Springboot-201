package com.mindtree.shoppingapp.utility;

import org.springframework.http.HttpStatus;

import com.mindtree.shoppingapp.dto.ApiResponse;

/**
 * @author M1049006
 *
 */
public class ResponseUtility {

	/**
	 * @param body
	 * @param status
	 * @param error
	 * @param message
	 * @return APIResponse
	 */
	public static ApiResponse responseBuilder(Object body, HttpStatus status, Boolean error, String message) {
		ApiResponse response = new ApiResponse();
		response.setBody(body);
		response.setError(error);
		response.setMessage(message);
		response.setStatus(status.toString());
		return response;
	}

}
