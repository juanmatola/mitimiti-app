package com.example.mitimiti.services.temporals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mitimiti.entities.temporals.Expense;
import com.example.mitimiti.entities.temporals.Participant;
import com.example.mitimiti.repository.temporals.ExpenseRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private ParticipantService participantService;

	public void createNewExpense(String detail, String amount, String buyerId, List<String> participantsIds) throws Exception{
		
		Expense expense = new Expense();
		
		expense.setDetail(detail);
		expense.setAmount(Double.parseDouble(amount));

		Participant buyer = participantService.getParticipantById(buyerId);
		expense.setBuyer(buyer);
		
		List<Participant> consumers = participantService.getParticipantsListByIds(participantsIds);
		expense.setConsumers(consumers);
		
		expenseRepository.save(expense);
		
	}

	public List<Expense> getAll() throws Exception{
		
		return expenseRepository.findAll();
		
	}
}
