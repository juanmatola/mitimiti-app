package com.example.mitimiti.controllers;

import java.util.HashMap;
import java.util.List;

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
import com.example.mitimiti.entities.Event;
import com.example.mitimiti.entities.Expense;
import com.example.mitimiti.entities.Participant;
import com.example.mitimiti.entities.Usuario;
import com.example.mitimiti.services.EventService;
import com.example.mitimiti.services.ExpenseService;
import com.example.mitimiti.util.exceptions.SessionException;

@Controller
@RequestMapping("/user/evento")
public class EventController extends BaseUserController {


	@Autowired
	private EventService eventService;
	@Autowired
	private ExpenseService expenseSerivice;
	
	
	@GetMapping()
	public String index(ModelMap model) {
		
		
		try {

			Usuario loggedUser = super.obtainLoggedUser();
			Event event = this.eventService.getEvent(loggedUser);
			List<Expense> expenses = this.expenseSerivice.getAllExpensesFromEvent(event);
			
			model.addAttribute("nombreEvento", event.getName());
			model.addAttribute("amigos", event.getParticipants());
			model.addAttribute("listagastos", expenses);
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
			
		}
		
		return ViewNames.EVENT;
	}
	
	@PostMapping()
	public String createEvento ( 	@RequestParam("name") String name,
									@RequestParam("participantes[]") List<String> friendsIDs) {
		
		
		try {
			
			eventService.createNewEvent(name, friendsIDs, super.obtainLoggedUser());
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
			
		}
		
		return RedirectTo.EVENT;
	}
	
	@GetMapping("/resumen")
	public String resumen(ModelMap model) {
		
		
		try {

			Usuario loggedUser = super.obtainLoggedUser();
			Event event = this.eventService.getEvent(loggedUser);
			//List<Expense> expenses = this.expenseSerivice.getAllExpensesFromEvent(event);
			
			HashMap<Participant, HashMap<String, Double>> resume = this.eventService.calcularCostos(loggedUser);
			
			model.addAttribute("nombreEvento", event.getName());
			model.addAttribute("resume", resume);
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
			
		}
		
		return ViewNames.RESUMEN;
	}
	
	@GetMapping("/delete")
	public String delete() {
				
		try {
			
			Usuario user = super.obtainLoggedUser();
			this.eventService.removeOldEventIfExists(user);
			
		} catch (Exception e) {
			
			this.errorHandle(e);
			
		}
		
		
		return RedirectTo.PANEL;
	}

	@Override
	public String errorHandle(Exception e) {
		
		if (e instanceof SessionException) {
			return RedirectTo.LOGIN;
		} else {			
			return RedirectTo.PANEL.concat("?err=").concat(e.getMessage());
		}
		
	}
}
