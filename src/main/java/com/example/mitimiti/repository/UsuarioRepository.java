package com.example.mitimiti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mitimiti.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
    public Optional<Usuario> findByName(String name);
	
    public Optional<Usuario> findByMail(String mail);
}
