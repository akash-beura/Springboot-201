package com.mindtree.shoppingapp.dto;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class ApiResponse {

	private String message;
	private Object body;
	private boolean error;
	private String status;

}
