package com.example.mitimiti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mitimiti.config.RedirectTo;
import com.example.mitimiti.config.ViewNames;
import com.example.mitimiti.controllers.basecontrollers.BaseUserController;
import com.example.mitimiti.services.UsuarioService;

@Controller
@RequestMapping("/user/profile")
public class ProfileController extends BaseUserController {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping()
	public String profile () {
		return ViewNames.PROFILE;
	}
	
	@Override
	public String errorHandle(Exception e) {
		return RedirectTo.PROFILE.concat("?err=").concat(e.getMessage());
	}

}
