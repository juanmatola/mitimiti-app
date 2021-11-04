package com.example.mitimiti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mitimiti.controllers.basecontrollers.BaseUserController;
import com.example.mitimiti.services.temporals.EventService;

@Controller
@RequestMapping("/user/evento")
public class EventController extends BaseUserController {


	@Autowired
	private EventService eventService;
	
	@PostMapping()
	public String createEvento ( 	@RequestParam("name") String name,
									@RequestParam("participantes[]") List<String> friendsIDs) {
		
		
		try {
			
			eventService.createNewEvent(name, friendsIDs, super.obtainLoggedUser());
			
		} catch (Exception e) {
			
			this.errorHandle(e);
			
		}
		
		return super.REDIRECT_TO_PANEL;
	}

	@Override
	public String errorHandle(Exception e) {
	
		return super.REDIRECT_TO_PANEL.concat("?err=").concat(e.getMessage());
		
	}
	
}
