package com.example.mitimiti.util;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public interface ErrorHandler {
	public String errorHandle(Exception e);
}
