package com.example.mitimiti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mitimiti.controllers.basecontrollers.BaseUserController;
import com.example.mitimiti.services.temporals.ExpenseService;

@Controller
@RequestMapping("/user/evento/gasto")
public class ExpenseController extends BaseUserController {

	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping()
	public String createExpense(@RequestParam("desc") String detail,
								@RequestParam("monto") String amount,
								@RequestParam("comprador") String buyerId,
								@RequestParam("participantes[]") List<String> participantsIds) {
		
		try {
			
			expenseService.createNewExpense(detail, amount, buyerId, participantsIds);
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
			
		}
		
		return super.REDIRECT_TO_EVENT;
	}
	

	@Override
	public String errorHandle(Exception e) {
		
		return super.REDIRECT_TO_EVENT.concat("?err=").concat(e.getMessage());
		
	}

}
