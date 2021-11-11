package com.example.mitimiti.controllers.rest;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mitimiti.config.RedirectTo;
import com.example.mitimiti.controllers.basecontrollers.BaseUserController;
import com.example.mitimiti.entities.Participant;
import com.example.mitimiti.entities.Usuario;
import com.example.mitimiti.services.EventService;

@RestController
public class BalanceResume extends BaseUserController{
	
	@Autowired
	private EventService eventService;
	
	@RequestMapping("/user/evento/resumen/resume")
	public HashMap<Participant, HashMap<String, Double>> index(){
		
		HashMap<Participant, HashMap<String, Double>> data = new HashMap<>();
		
		try {
			
			Usuario user = super.obtainLoggedUser();
			
			data = this.eventService.calcularCostos(user);
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return data;
		
	}
	
	public HashMap<String, Double> balanceResume(HashMap<Participant, HashMap<String, Double>> incommingData){
		
		
		return null;
	}

	@Override
	public String errorHandle(Exception e) {
		
		return RedirectTo.LOGIN;
		
	}
}
