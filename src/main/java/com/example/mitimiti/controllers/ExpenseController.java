package com.example.mitimiti.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mitimiti.controllers.basecontrollers.BaseUserController;

@Controller
@RequestMapping("/user/evento/gasto")
public class ExpenseController extends BaseUserController {
	
	
	@PostMapping()
	public String createExpense(@RequestParam("desc") String description,
								@RequestParam("monto") String monto,
								@RequestParam("comprador") String buyerId,
								@RequestParam("participantes[]") List<String> participantsIds) {

		System.err.println("Datos del gasto:");
		System.err.println(" Detalle:" + description);
		System.err.println(" Comprador: " + buyerId);
		System.err.println(" Monto: " + monto);
		System.err.println(" Consumidores: ");
		for (String participant : participantsIds) {
			System.err.println("  " + participant);
		}
		
		return super.REDIRECT_TO_EVENT;
	}
	

	@Override
	public String errorHandle(Exception e) {
		
		return super.REDIRECT_TO_EVENT.concat("?err=").concat(e.getMessage());
		
	}

}
