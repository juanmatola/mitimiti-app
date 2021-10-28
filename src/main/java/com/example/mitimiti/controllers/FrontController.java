package com.example.mitimiti.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mitimiti.config.ViewNames;
import com.example.mitimiti.util.ErrorHandler;

@Controller
@RequestMapping("/")
public class FrontController implements ErrorHandler {
	
	@GetMapping()
	public String index () {
		return ViewNames.INDEX;
	}
	
	@GetMapping("/login")
	public String login () {
		return ViewNames.LOGIN;
	}
	
	@GetMapping("/singup")
	public String singup () {
		return ViewNames.SING_UP;
	}
	
	@PostMapping("/sing-up")
	public String singupPost(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		return "redirect:/";
	}

	@Override
	public String errorHandle(Exception e, ModelMap model) {
		// TODO implementar para manejo de exceptions
		return null;
	}
}
