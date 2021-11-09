package com.example.mitimiti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mitimiti.config.ViewNames;
import com.example.mitimiti.controllers.basecontrollers.BaseUserController;
import com.example.mitimiti.entities.Event;
import com.example.mitimiti.entities.Expense;
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
		
		return super.REDIRECT_TO_EVENT;
	}

	@Override
	public String errorHandle(Exception e) {
		
		if (e instanceof SessionException) {
			return super.REDIRECT_TO_LOGIN;
		} else {			
			return super.REDIRECT_TO_PANEL.concat("?err=").concat(e.getMessage());
		}
		
	}
	
	//Unicamente para probar el calcular costos
	@GetMapping("/prueba")
	public String index2(ModelMap model) {
		
		
		try {

			Usuario loggedUser = super.obtainLoggedUser();
			Event event = this.eventService.getEvent(loggedUser);
			List<Expense> expenses = this.expenseSerivice.getAllExpensesFromEvent(event);
			this.eventService.calcularCostos(loggedUser);
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
			
		}
		
		return ViewNames.EVENT;
	}
	
}
