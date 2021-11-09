package com.example.mitimiti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mitimiti.entities.Event;
import com.example.mitimiti.entities.Usuario;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

	public Optional<Event> findByUsuario(Usuario usuario);
	
	public void deleteAllByUsuario(Usuario usuario);

}
