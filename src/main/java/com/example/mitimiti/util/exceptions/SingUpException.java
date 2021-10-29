package com.example.mitimiti.util.exceptions;

@SuppressWarnings("serial")
public class SingUpException extends Exception{
	
	public SingUpException() {}

	public SingUpException(String message){
		super(message);
	}
}