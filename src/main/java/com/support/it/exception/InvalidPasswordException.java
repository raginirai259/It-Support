package com.support.it.exception;

public class InvalidPasswordException extends ITSupportAppException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * This Exception is thrown from RegistrationService class with error message
	 * RegistrationService.INVALID_PASSWORD if the given password is not matching
	 * the constraints given in the regular expression.
	 * 
	 * 
	 *
	 */

	public InvalidPasswordException(String message) {
		super(message);
	}
}