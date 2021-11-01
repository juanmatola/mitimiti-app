package com.example.mitimiti.util;

import java.util.ArrayList;
import java.util.List;

import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import com.example.mitimiti.config.UserDataRestriction;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;

public class ValidationUtils {
	
	public static boolean validatePassword(String password) {
		
		  List<Rule> rules = new ArrayList<>();        
	      rules.add(new LengthRule(UserDataRestriction.PASSWORD_MIN_LENGTH, UserDataRestriction.PASSWORD_MAX_LENGTH));   
	      if(UserDataRestriction.PASSWORD_NO_WHITESPACE_VALIDATION) rules.add(new WhitespaceRule());
	      if(UserDataRestriction.PASSWORD_UPPER_CASE_VALIDATION) rules.add(new CharacterRule(EnglishCharacterData.UpperCase, UserDataRestriction.PASSWORD_MIN_UPPER_CASE_CHARACTERS));        
	      if(UserDataRestriction.PASSWORD_LOWER_CASE_VALIDATION) rules.add(new CharacterRule(EnglishCharacterData.LowerCase, UserDataRestriction.PASSWORD_MIN_LOWER_CASE_CHARACTERS));        
	      if(UserDataRestriction.PASSWORD_DIGITS_VALIDATION) rules.add(new CharacterRule(EnglishCharacterData.Digit, UserDataRestriction.PASSWORD_MIN_ALLOWED_DIGITS));        
	        
	      PasswordValidator validator = new PasswordValidator(rules);        
	      PasswordData passwordData = new PasswordData(password);        
	      RuleResult result = validator.validate(passwordData);
	        
	      return result.isValid();
	}
	
	public static boolean validateUsername(String username) {
		
		  List<Rule> rules = new ArrayList<>();        
	      rules.add(new LengthRule(UserDataRestriction.USERNAME_MIN_LENGTH, UserDataRestriction.USERNAME_MAX_LENGTH));        
	      if(UserDataRestriction.USERNAME_NO_WHITESPACE_VALIDATION) rules.add(new WhitespaceRule());             
	        
	      PasswordValidator validator = new PasswordValidator(rules);        
	      PasswordData usernameData = new PasswordData(username);        
	      RuleResult result = validator.validate(usernameData);
	        
	      return result.isValid();
	}
	
}
