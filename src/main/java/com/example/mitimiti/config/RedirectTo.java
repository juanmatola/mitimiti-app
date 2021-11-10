package com.example.mitimiti.config;

public final class RedirectTo {
	private static final String redirect = "redirect:";
	public static final String LOGIN = redirect.concat("/?action=login");
	public static final String PANEL = redirect.concat("/user");
	public static final String EVENT = redirect.concat("/user/evento");
	
	private RedirectTo() {}
}
