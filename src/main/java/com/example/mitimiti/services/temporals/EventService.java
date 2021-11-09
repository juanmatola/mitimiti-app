package com.example.mitimiti.services.temporals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mitimiti.entities.Usuario;
import com.example.mitimiti.entities.temporals.Event;
import com.example.mitimiti.entities.temporals.Expense;
import com.example.mitimiti.entities.temporals.Participant;
import com.example.mitimiti.repository.temporals.EventRepository;
import com.example.mitimiti.util.exceptions.EventException;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private ParticipantService participantService;
	@Autowired
	private ExpenseService expenseService;

	public void createNewEvent(String name, List<String> friendsIDs, Usuario usuario) throws Exception {
		
		this.removeOldEventIfExists(usuario);
		
		Event event = new Event();
		event.setName(name);
		event.setDate(new Date());		
		event.setUsuario(usuario);
		event.setParticipants(participantService.gerateParticipantsListByFriendIDs(friendsIDs));
		
		eventRepository.save(event);
		
	}
	
	public Event getEvent(Usuario loggedUser) throws EventException {
		
		try {
			return eventRepository.findByUsuario(loggedUser).get();
		}catch(Exception e) {
			throw new EventException("El usuario no cuenta con eventos");
		}
		
	}
	
	private void removeOldEventIfExists(Usuario usuario) throws Exception {
		
		try {
			
			Optional<Event> evento = eventRepository.findByUsuario(usuario);
			
			if(evento.isPresent()) {
				this.deleteEvent(evento.get());
			}
			
		}catch(Exception e) {
			
			throw new Exception("Error al remover el evento anterior");
			
		}
		
	}
	
	private void deleteEvent(Event event) throws Exception{
		
		expenseService.removeAllConsumersByEvent(event);
		
		eventRepository.deleteById(event.getId());
		
	}
	
	public void calcularCostos(Usuario loggedUser) throws Exception {
		Event event = getEvent(loggedUser);
		List<Participant> participants = event.getParticipants();
		List<Expense> expenses = expenseService.getAllExpensesFromEvent(event);
		HashMap<String, List<HashMap<String, Double>>> listOfIndividualTotals = new HashMap<String, List<HashMap<String, Double>>>();
		participants.forEach(participant -> {
			listOfIndividualTotals.put(participant.getName(), getIndividualExpenses(participant, expenses));
		});
		
		//INFO VISUALIZADA EN LA CONSOLA action=/user/evento/prueba
		for(Map.Entry<String, List<HashMap<String, Double>>> ex : listOfIndividualTotals.entrySet()) {
			System.out.println("--------------------");
			System.out.println("Resumen de: " + ex.getKey());
			for(HashMap<String, Double> exx : ex.getValue()) {
				for(Map.Entry<String, Double> exDetails : exx.entrySet()) {
					System.out.println(exDetails.getKey() + " : " + exDetails.getValue());
				}
				System.out.println("----------");
			}
		}
	}
	
	private List<HashMap<String, Double>> getIndividualExpenses(Participant participant, List<Expense> expenses){
		HashMap<String, Double> individualExpenses = new HashMap();
		HashMap<String, Double> buyerInformation = new HashMap();
		
		for(Expense expense : expenses) {
			boolean isParticipant = expense.getConsumers().stream().filter(p -> p.getId().equals(participant.getId())).findFirst().isPresent();
			boolean isBuyer = expense.getBuyer().getId().equals(participant.getId());
			
			if (isBuyer) {
				if (isParticipant) {
					individualExpenses.put("Gastos: " + expense.getDetail(), (expense.getAmount() / expense.getConsumers().size()));
					buyerInformation.put("Cobro por: " + expense.getDetail(), (expense.getAmount() - (expense.getAmount() / expense.getConsumers().size())));
				}else {
					individualExpenses.put("Gastos: " + expense.getDetail(), 0.0);
					buyerInformation.put("Cobro por: " + expense.getDetail(), expense.getAmount());
				}
			}else {
				buyerInformation.put("Cobro por: " + expense.getDetail(), 0.0);
				if (isParticipant) {
					individualExpenses.put("Gastos: " + expense.getDetail(), (expense.getAmount() / expense.getConsumers().size()));
				}else {
					individualExpenses.put("Gastos: " + expense.getDetail(), 0.0);
				}
			}
		}
		List<HashMap<String, Double>> totalInformation = new ArrayList();
		totalInformation.add(individualExpenses);
		totalInformation.add(buyerInformation);
		return totalInformation;
	}

}
