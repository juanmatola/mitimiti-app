package com.example.mitimiti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mitimiti.entity.Friend;
import com.example.mitimiti.entity.Usuario;

@Repository
public interface FriendRepository extends JpaRepository<Friend, String>{
	
	public Optional<Friend> findByMail(String mail);
	
	public List<Friend> findByUsuario(Usuario usuario);
}
