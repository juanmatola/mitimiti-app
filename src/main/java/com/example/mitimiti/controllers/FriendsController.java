package com.example.mitimiti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mitimiti.services.FriendService;

@Controller
@RequestMapping("/user/friends")
public class FriendsController {
	
	@Autowired
	private FriendService friendService;
	
	@PostMapping("/create")
	public void createNewUsuario(	@RequestParam("name") String name,
									@RequestParam("mail") String mail) {
		
		
		
	}
	
}
