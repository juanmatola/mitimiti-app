package com.example.mitimiti.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mitimiti.util.exceptions.SingUpException;

@Service
public class UsuarioService implements UserDetailsService{

	public void createNewUsuario(String username, String password, String password_2) throws SingUpException {
		
		this.validate(password, password, password_2);
		
	}
	
	private void validate(String email, String password_1, String password_2) throws SingUpException{
		
		if (email == null || email.isEmpty()) {
			throw new SingUpException("El email no puede ser nulo");
		}
		if (password_1 == null || password_1.isEmpty()) {
			throw new SingUpException("La contraseña no puede ser nula");
		}
		if (!password_1.equals(password_2)) {
			throw new SingUpException("Ingrese correctamente las contraseñas");
		}
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
