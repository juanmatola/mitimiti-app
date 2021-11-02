package com.example.mitimiti.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mitimiti.entity.Friend;
import com.example.mitimiti.entity.Usuario;
import com.example.mitimiti.repository.FriendRepository;

@Service
public class FriendService {

	@Autowired
	private FriendRepository friendRepository;
	
	public void createNewFriend(String mail, 
								String name, 
								Usuario usuario) throws Exception{
	
		Friend friend = new Friend();
		friend.setMail(mail);
		friend.setName(name);
		friend.setUsuario(usuario);
		
		try {
			friendRepository.save(friend);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception("Error al insertar el nuevo amigo");
		}
		
	}
	
	public void updateFriend(	String id,
								String mail, 
								String nombre) throws Exception{
		
		Friend friend = this.getFriendById(id);
		
		friend.setMail(mail);
		friend.setName(nombre);
		
		friendRepository.save(friend);
		
	}
	
	public void deletFriendById(String id) throws Exception{
		
		try {			
			friendRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception("Id incorrecto");	
		}
		
	}
	
	public List<Friend> getFriendsByUsuario(Usuario usuario){
		
		return friendRepository.findByUsuario(usuario);
		
	}
	
	
	public Friend getFriendById(String id) throws Exception {
		
		Optional<Friend> queryResponse = friendRepository.findById(id);
		
		if (queryResponse.isPresent()) {
			return queryResponse.get();
		}else {
			throw new Exception("No existe amigo con dicho id");
		}
		
	}
	
}
