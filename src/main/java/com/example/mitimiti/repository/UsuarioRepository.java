package com.example.mitimiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mitimiti.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	@Query("SELECT u FROM Usuario u WHERE u.name = :name")
    public Usuario getUserByName(@Param("name") String name);
	
	@Query("SELECT u FROM Usuario u WHERE u.mail = :mail")
    public Usuario getUserByMail(@Param("mail") String mail);
}
