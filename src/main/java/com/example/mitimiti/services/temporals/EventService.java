package com.example.mitimiti.services.temporals;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mitimiti.entities.Usuario;
import com.example.mitimiti.entities.temporals.Event;
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

}
