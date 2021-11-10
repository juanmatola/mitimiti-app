package com.example.mitimiti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mitimiti.config.RedirectTo;
import com.example.mitimiti.controllers.basecontrollers.BaseUserController;
import com.example.mitimiti.services.ExpenseService;

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
		
		return RedirectTo.EVENT;
	}
	
	@GetMapping("/delete/{id}")
	public String deleteExpense(@PathVariable("id") String id) {
		
		try {
			
			expenseService.deleteById(id);
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
			
		}
		
		return RedirectTo.EVENT;
	}
	

	@Override
	public String errorHandle(Exception e) {
		
		return RedirectTo.EVENT.concat("?err=").concat(e.getMessage());
		
	}

}
