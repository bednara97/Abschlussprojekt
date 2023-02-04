package com.bednara.exceptions;

public class UserNameOrPasswordWrongException extends Exception {

	public UserNameOrPasswordWrongException(String errorMessage) {
		super(errorMessage);
	}
}
