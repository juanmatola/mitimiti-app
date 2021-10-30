package com.example.mitimiti.config;

import org.springframework.stereotype.Component;

@Component
public class UserDataRestriction {
	public static final int PASSWORD_MIN_LENGTH = 8;
	public static final int PASSWORD_MAX_LENGTH = 16;
	
	public static final boolean PASSWORD_NO_WHITESPACE_VALIDATION = true;
	
	public static final boolean PASSWORD_UPPER_CASE_VALIDATION = true;
	public static final int PASSWORD_MIN_UPPER_CASE_CHARACTERS = 1;
	
	public static final boolean PASSWORD_LOWER_CASE_VALIDATION = true;
	public static final int PASSWORD_MIN_LOWER_CASE_CHARACTERS = 1;

	public static final boolean PASSWORD_DIGITS_VALIDATION = true;
	public static final int PASSWORD_MIN_ALLOWED_DIGITS = 1;
	
	public static final int USERNAME_MIN_LENGTH = 4;
	public static final int USERNAME_MAX_LENGTH = 16;
	
	public static final boolean USERNAME_NO_WHITESPACE_VALIDATION = true;
}
