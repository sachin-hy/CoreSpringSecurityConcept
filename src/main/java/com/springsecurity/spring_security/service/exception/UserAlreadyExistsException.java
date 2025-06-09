package com.springsecurity.spring_security.service.exception;

public class UserAlreadyExistsException extends Exception {

	public UserAlreadyExistsException(String message)
	{
		super(message);
	}
}
