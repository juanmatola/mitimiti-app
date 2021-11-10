package com.example.mitimiti.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mitimiti.entities.Event;
import com.example.mitimiti.entities.Expense;
import com.example.mitimiti.entities.Participant;
import com.example.mitimiti.entities.Usuario;
import com.example.mitimiti.repository.EventRepository;
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
	
	public void removeOldEventIfExists(Usuario usuario) throws Exception {
		
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
	
	public HashMap<Participant, HashMap<String, Double>> calcularCostos(Usuario loggedUser) throws Exception {
		Event event = getEvent(loggedUser);
		List<Participant> participantsList = event.getParticipants();
		List<Expense> expenses = expenseService.getAllExpensesFromEvent(event);
		
		HashMap<Participant, HashMap<String, Double>> resumen = new HashMap<Participant, HashMap<String, Double>>();
		
		for (Participant participant : participantsList) {
			resumen.put(participant, this.getParticipantDetail(participant, expenses));
		}
		
		
		return resumen;
		
	}
	
	private HashMap<String, Double> getParticipantDetail(Participant participant, List<Expense> expenses){
		
		HashMap<String, Double> participantDetail = new HashMap<String, Double>();
		String participantId = participant.getId();
		List<Participant> expenseConsumers;
		Double consumoTotal = 0.0;
		Double aporteTotal = 0.0;
		
		for (Expense expense : expenses) {
		
			if (participant.getId().equals(expense.getBuyer().getId())) {
				aporteTotal = aporteTotal + expense.getAmount();
			}
			
			expenseConsumers = expense.getConsumers();
			
			boolean isConsumer = expenseConsumers.stream().filter(consumer -> consumer.getId().equals(participantId)).findFirst().isPresent();
			
			if (isConsumer) {
				consumoTotal = consumoTotal + (expense.getAmount()/expenseConsumers.size());
			}
			
		}
		
		participantDetail.put("aporteTotal", aporteTotal);
		participantDetail.put("consumoTotal", consumoTotal);
		participantDetail.put("saldo", (aporteTotal-consumoTotal) );
		
		return participantDetail;
	}
	
	@SuppressWarnings("unused")
	private void showResumeInConsole( HashMap<Participant, HashMap<String, Double>> resumen ) {
		
		for (Map.Entry<Participant, HashMap<String, Double>> participantDetail : resumen.entrySet()) {
			Participant participant = participantDetail.getKey();
			HashMap<String, Double> expenseResumen = participantDetail.getValue();
			
			System.err.println("------------------");
			System.err.println(participant.getName());
			System.out.println("Consumo total: " + expenseResumen.get("consumoTotal"));
			System.out.println("Aporte total: " + expenseResumen.get("aporteTotal"));
			System.out.println("Saldo: " + expenseResumen.get("saldo"));
			
		}
		
	}

}