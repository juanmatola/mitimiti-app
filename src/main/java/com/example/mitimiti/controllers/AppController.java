package com.example.mitimiti.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mitimiti.config.ViewNames;

@Controller
@RequestMapping("/user")
public class AppController {
	
	@GetMapping()
	public String index () {
		return ViewNames.PANEL;
	}
	
}
