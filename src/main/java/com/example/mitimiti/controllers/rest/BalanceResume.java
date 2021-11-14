package com.example.mitimiti.controllers.rest;

import java.util.HashMap;
import java.util.Map;

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
	public HashMap<String, Double> index(){
		
		HashMap<Participant, HashMap<String, Double>> data = new HashMap<>();
		HashMap<String, Double> balance = new HashMap<String, Double>();
		
		try {
			
			Usuario user = super.obtainLoggedUser();
			
			data = this.eventService.calcularCostos(user);
			
			balance = this.balanceResume(data);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return balance;
		
	}
	
	public HashMap<String, Double> balanceResume(HashMap<Participant, HashMap<String, Double>> incommingData){
		
		HashMap<String, Double> balance = new HashMap<String, Double>();
		
		for (Map.Entry<Participant, HashMap<String, Double>> entry : incommingData.entrySet()) {
			Participant participant = entry.getKey();
			HashMap<String, Double> resume = entry.getValue();
			
			balance.put(participant.getName(), resume.get("balance"));

		}
		
		return balance;
	}

	@Override
	public String errorHandle(Exception e) {
		
		return RedirectTo.LOGIN;
		
	}
}
