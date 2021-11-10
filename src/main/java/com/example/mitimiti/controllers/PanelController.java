package com.example.mitimiti.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mitimiti.config.RedirectTo;
import com.example.mitimiti.config.ViewNames;
import com.example.mitimiti.controllers.basecontrollers.BaseUserController;
import com.example.mitimiti.entities.Event;
import com.example.mitimiti.entities.Friend;
import com.example.mitimiti.entities.Usuario;
import com.example.mitimiti.services.EventService;
import com.example.mitimiti.services.FriendService;
import com.example.mitimiti.util.exceptions.EventException;

@Controller
@RequestMapping("/user")
public class PanelController extends BaseUserController{
	
	@Autowired
	private FriendService friendService;
	@Autowired
	private EventService eventService;
	
	@GetMapping()
	public String index (ModelMap model) {
		
		try {	
			
			Usuario usuarioLogeado = this.obtainLoggedUser();
			
			List<Friend> amigos = friendService.getFriendsByUsuario(usuarioLogeado); 
			model.addAttribute("amigos", amigos);
	
			Event event = eventService.getEvent(usuarioLogeado);
			model.addAttribute("event", event);
			
		} catch (Exception e) {
			if (!(e instanceof EventException)) {	
				return this.errorHandle(e);
			}			
		}
		
		return ViewNames.PANEL;
	}


	@Override
	public String errorHandle(Exception e) {
		
		return RedirectTo.LOGIN;
		
	}

	
}
