package com.example.mitimiti.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mitimiti.entities.Event;
import com.example.mitimiti.entities.Expense;
import com.example.mitimiti.entities.Participant;
import com.example.mitimiti.repository.ExpenseRepository;

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
	
	public void removeAllConsumersByEvent(Event event) throws Exception{
		
		List<Expense> expenses = this.getAllExpensesFromEvent(event);
		
		for (Expense expense : expenses) {
			expense.setConsumers(null);
		}
		
		expenseRepository.saveAll(expenses);
	}


	public List<Expense> getAllExpensesFromEvent(Event event) throws Exception{
		
		List<Participant> participants = event.getParticipants();
		
		List<Expense> expenses = new ArrayList<Expense>();
		
		for (Participant participant : participants) {
			expenses.addAll(expenseRepository.findByBuyer(participant));
		}
		
		return expenses;
	}
	
	public List<Expense> getAll() throws Exception{
		
		return expenseRepository.findAll();
		
	}

	public void deleteById(String id) throws Exception {


		this.removeConsumersByExpenseId(id);
		
		expenseRepository.deleteById(id);

	}
	
	private void removeConsumersByExpenseId(String id) throws Exception {
		
		Expense expense = this.getExpenseById(id);
		
		expense.setConsumers(null);
		
		expenseRepository.save(expense);
	}
	
	public Expense getExpenseById(String id) throws Exception {
		
		Optional<Expense> res = expenseRepository.findById(id);
		
		if (res.isPresent()) {
			return res.get();
		}else{
			throw new Exception("Id incorrecto");
		}
		
	}
	
}
