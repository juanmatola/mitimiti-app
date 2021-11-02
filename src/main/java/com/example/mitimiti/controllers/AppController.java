package com.example.mitimiti.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.mitimiti.config.ViewNames;
import com.example.mitimiti.entity.Friend;
import com.example.mitimiti.entity.Usuario;
import com.example.mitimiti.services.FriendService;

@Controller
@RequestMapping("/user")
public class AppController {
	
	
	
	@Autowired
	private FriendService friendService;
	private String loginPath = "?action=login";
	
	@GetMapping()
	public String index (ModelMap model) {
		
		try {	
			
			Usuario usuarioLogeado = this.obtainLoggedUser();
			
			List<Friend> amigos = friendService.getFriendsByUsuario(usuarioLogeado); 
			
			model.addAttribute("amigos", amigos);
			
		} catch (Exception e) {
			return "redirect:/".concat(loginPath);
		}
		
		return ViewNames.PANEL;
	}

	private Usuario obtainLoggedUser() throws Exception {
		
 		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

 		HttpSession session = attr.getRequest().getSession(false);
 		
 		Usuario sessionUsuario = (Usuario) session.getAttribute("clientesession");
 		
 		if (sessionUsuario != null) {
			return sessionUsuario;
		}else {
			throw new Exception("No user session");
		}

	}
	
}
