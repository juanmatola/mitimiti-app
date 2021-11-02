package com.example.mitimiti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mitimiti.controllers.basecontrollers.BaseUserController;
import com.example.mitimiti.entity.Usuario;
import com.example.mitimiti.services.FriendService;

@Controller
@RequestMapping("/user/friends")
public class FriendsController extends BaseUserController {
	
	@Autowired
	private FriendService friendService;
	
	@PostMapping("/create")
	public String createNewFriend(	@RequestParam("name") String name,
									@RequestParam("mail") String mail) {
		
		try {		
			
			Usuario usuario = this.obtainLoggedUser();
			
			friendService.createNewFriend(mail, name, usuario);
						
		} catch (Exception e) {
			return "redirect:/".concat(loginPath);
		}
		
		return "redirect:/user";
		
	}
	
}
