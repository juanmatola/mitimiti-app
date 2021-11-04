package com.example.mitimiti.repository.temporals;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mitimiti.entities.Usuario;
import com.example.mitimiti.entities.temporals.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

	public Optional<Event> findByUsuario(Usuario usuario);
}
