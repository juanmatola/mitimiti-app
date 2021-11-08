package com.example.mitimiti.services.temporals;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mitimiti.entities.Friend;
import com.example.mitimiti.entities.temporals.Participant;
import com.example.mitimiti.repository.temporals.ParticipantRepository;
import com.example.mitimiti.services.FriendService;

@Service
public class ParticipantService {

	@Autowired
	private ParticipantRepository participantRepository;
	@Autowired
	private FriendService friendService;
	
	public Participant getParticipantById (String id) throws Exception{
		
		return participantRepository.getById(id);
		
	}

	public List<Participant> getParticipantsListByIds(List<String> participantsIds) throws Exception {

		return participantRepository.findAllById(participantsIds);
				
	}
	
	public List<Participant> gerateParticipantsListByFriendIDs(List<String> friendsIDs) throws Exception {
		
		
		List<Friend> amigosAConvertir = friendService.getFriendsByIdList(friendsIDs);
		List<Participant> participantesList = new ArrayList<Participant>();
		
		for (Friend friend : amigosAConvertir) {
			
			Participant participante = new Participant();
			participante.setName(friend.getName());
			participante.setMail(friend.getMail());
			
			participantesList.add(participante);
			
		}
		
		participantRepository.saveAll(participantesList);
		
		return participantesList;
	}

}
