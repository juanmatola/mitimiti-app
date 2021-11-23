package com.example.mitimiti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mitimiti.config.RedirectTo;
import com.example.mitimiti.config.ViewNames;
import com.example.mitimiti.controllers.basecontrollers.BaseUserController;
import com.example.mitimiti.entities.Usuario;
import com.example.mitimiti.services.EventService;
import com.example.mitimiti.services.UsuarioService;
import com.example.mitimiti.util.exceptions.SessionException;

@Controller
@RequestMapping("/user/profile")
public class ProfileController extends BaseUserController {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private EventService eventService;

	@GetMapping()
	public String profile (ModelMap model) {
		
		try {		
			
			Usuario loggedUser = super.obtainLoggedUser();
			model.addAttribute("user", loggedUser);
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
			
		}
		
		
		return ViewNames.PROFILE;
	}
	
	@GetMapping("/delete-account")
	public String deleteAccount() {
		
		try {
			
			Usuario loggedUser = super.obtainLoggedUser();
			this.eventService.removeOldEventIfExists(loggedUser);
			this.usuarioService.deleteUsuarioById(loggedUser.getId());
			
		} catch (Exception e) {
			return this.errorHandle(e);
		}
		
		return RedirectTo.HOME;
	}
	
	@Override
	public String errorHandle(Exception e) {
		
		if (e instanceof SessionException) {
			return RedirectTo.LOGIN;
		}
		
		return RedirectTo.PROFILE.concat("?err=").concat(e.getMessage());
	}

}
