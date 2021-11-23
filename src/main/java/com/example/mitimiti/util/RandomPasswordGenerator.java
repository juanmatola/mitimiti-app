package com.example.mitimiti.util;

import net.bytebuddy.utility.RandomString;

public class RandomPasswordGenerator {

	private final int DEFAULT_LENGHT = 30;
	private RandomString randomString;
	
	public RandomPasswordGenerator() {
		this.randomString = new RandomString(this.DEFAULT_LENGHT);
	}
	
	public RandomPasswordGenerator(int lenght) {
		this.randomString = new RandomString(lenght);
	}
	
	public String generate() {
		return this.randomString.nextString();
	}
	
	
}
