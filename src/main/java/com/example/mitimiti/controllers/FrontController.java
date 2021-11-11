package com.example.mitimiti.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mitimiti.config.RedirectTo;
import com.example.mitimiti.config.ViewNames;
import com.example.mitimiti.controllers.basecontrollers.BaseUserController;
import com.example.mitimiti.services.SendService;
import com.example.mitimiti.services.UsuarioService;

@Controller
@RequestMapping("/")
public class FrontController extends BaseUserController {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private SendService sendService;
	
	@GetMapping()
	public String index (ModelMap model) {
		return ViewNames.INDEX;
	}
	
	@PostMapping("/sing-up")
	public String singupPost(ModelMap model, @RequestParam("username") String username, 
											 @RequestParam("password") String password,
											 @RequestParam("password2") String password2,
											 @RequestParam("email") Optional<String> mail){
		
		try {

			usuarioService.createNewUsuario(username, password, password2, mail);
			
			this.sendService.sayHello(username , mail);
			
		} catch (Exception e) {
			return this.errorHandle(e);
		}
		
		return RedirectTo.LOGIN;
	}

	@Override
	public String errorHandle(Exception e) {
		
		return "/?err=".concat(e.getMessage());

	}
}
