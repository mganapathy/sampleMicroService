package com.sapient.carbooking.com.sapient.carbooking.exception;

public class UserNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3915996206503546226L;
	/**
	 * 
	 */

	private String details;

	public UserNotFoundException(String details) {
		super();
		this.details = details;
	}

	public String getDetails() {
		return details;
	}
}
