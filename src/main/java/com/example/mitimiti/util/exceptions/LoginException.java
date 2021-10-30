package com.example.mitimiti.util.exceptions;

@SuppressWarnings("serial")
public class LoginException extends Exception{
	public LoginException() {}

	public LoginException(String message){
		super(message);
	}
}
