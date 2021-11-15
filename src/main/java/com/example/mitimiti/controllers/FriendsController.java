package com.example.mitimiti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mitimiti.config.RedirectTo;
import com.example.mitimiti.config.ViewNames;
import com.example.mitimiti.controllers.basecontrollers.BaseUserController;
import com.example.mitimiti.entities.Usuario;
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
			return this.errorHandle(e);
		}
		
		return RedirectTo.PANEL;

	}
	
	@GetMapping("/delete/{id}")
	public String deleteFriend (@PathVariable("id") String id) {
		
		try {
			
			friendService.deletFriendById(id);
			
			
		} catch (Exception e) {
		
			return this.errorHandle(e);			
		}
		
		return RedirectTo.PANEL;
		
	}
	
	@GetMapping("/update/{id}")
	public String updateFriend(ModelMap model, @PathVariable("id") String friendId) {
		
		try {
			
			model.addAttribute("friend", friendService.getFriendById(friendId));
			
		} catch (Exception e) {
			return this.errorHandle(e);
		}
		
		return ViewNames.UPDATE_FRIEND;
	}
	
	@PostMapping("/update/{id}")
	public String updateFriend(	@PathVariable("id") String id, 
								@RequestParam("name") String name, 
								@RequestParam("email") String email) {
		
		try {
			friendService.updateFriend(id, name, email);
		} catch (Exception e) {
			return this.errorHandle(e);
		}
		
		return RedirectTo.PANEL;
	}
	
	
	@Override
	public String errorHandle(Exception e) {
		
		return RedirectTo.PANEL.concat("?err=").concat(e.getMessage());

	}
	
}
