package com.example.mitimiti.util;

import org.springframework.stereotype.Component;

@Component
public interface ErrorHandler {
	public String errorHandle(Exception e);
}
