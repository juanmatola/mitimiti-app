package com.example.mitimiti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mitimiti.config.ViewNames;
import com.example.mitimiti.services.UsuarioService;
import com.example.mitimiti.util.ErrorHandler;
import com.example.mitimiti.util.exceptions.LoginException;
import com.example.mitimiti.util.exceptions.SingUpException;

@Controller
@RequestMapping("/")
public class FrontController implements ErrorHandler {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping()
	public String index (ModelMap model) {
		return ViewNames.INDEX;
	}
	
	@GetMapping("/login")
	public String login (ModelMap... model) {
		return ViewNames.LOGIN;
	}
	
	@GetMapping("/singup")
	public String singup (ModelMap model) {
		return ViewNames.SING_UP;
	}
	
	@PostMapping("/sing-up")
	public String singupPost(ModelMap model, @RequestParam("username") String username, @RequestParam("password") String password) {
		
		try {
			usuarioService.createNewUsuario(username, password);
		} catch (Exception e) {
			this.errorHandle(e, model);
		}
		
		return this.login();
	}

	@Override
	public String errorHandle(Exception e, ModelMap model) {
		
		model.addAttribute("err", e.getMessage());

		if (e instanceof SingUpException) {
			return this.singup(model);
		}else if(e instanceof LoginException) {
			return this.login(model);
		}else {			
			return this.index(model);
		}
		
	}
}
