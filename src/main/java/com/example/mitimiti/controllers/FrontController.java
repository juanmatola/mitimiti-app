package com.example.mitimiti.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mitimiti.config.ViewNames;
import com.example.mitimiti.util.ErrorHandler;

@Controller
@RequestMapping("/")
public class FrontController implements ErrorHandler {
	
	@GetMapping()
	public String index () {
		return ViewNames.INDEX;
	}

	@Override
	public String errorHandle(Exception e, ModelMap model) {
		// TODO implementar para manejo de exceptions
		return null;
	}
}
