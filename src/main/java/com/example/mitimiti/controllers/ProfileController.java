package com.example.mitimiti.controllers;


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
	
	@PostMapping("/change-password")
	public String changePassword( 	@RequestParam("currentPassword") String currentPassword,
									@RequestParam("newPassword") String newPassword,
									@RequestParam("newPassword2") String newPassword2) {
		
		
		try {
			
			this.usuarioService.changePassword(currentPassword, newPassword, newPassword2);
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
			
		}
		
		
		
		return RedirectTo.PROFILE;
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
		
		return RedirectTo.SING_UP;
	}
	
	@Override
	public String errorHandle(Exception e) {
		
		if (e instanceof SessionException) {
			return RedirectTo.LOGIN;
		}
		
		return RedirectTo.PROFILE.concat("?err=").concat(e.getMessage());
	}

}
