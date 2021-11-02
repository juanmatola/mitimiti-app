package com.example.mitimiti.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.mitimiti.entity.Usuario;
import com.example.mitimiti.repository.UsuarioRepository;
import com.example.mitimiti.util.ValidationUtils;
import com.example.mitimiti.util.exceptions.SingUpException;
 
@Service
public class UsuarioService implements UserDetailsService {
 
    @Autowired
    private UsuarioRepository usuarioRepository;
     
 	@Override
 	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

 		Usuario usuario = this.findByName(name);

 		List<GrantedAuthority> permisos = new ArrayList<>();

 		GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + "USER");
 		permisos.add(p1);

 		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

 		HttpSession session = attr.getRequest().getSession(true);
 		session.setAttribute("clientesession", usuario);

 		return new User(usuario.getName(), usuario.getPassword(), permisos);
 	}
 	
 	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Usuario createNewUsuario(String name, String password, String password_2, Optional<String> mail) throws SingUpException {

 		validate(name, password, password_2, mail.orElse(null));
 		
 		String encryptedKey = new BCryptPasswordEncoder().encode(password);
 		
 		Usuario usuario = new Usuario();
 		usuario.setName(name);
 		usuario.setPassword(encryptedKey);
 		if(mail.isPresent()) usuario.setMail(mail.get());

		try {
			usuarioRepository.save(usuario);
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new SingUpException("Hubo un error al crear el nuevo usuario, intente de nuevo más tarde");
		}
	}

 	private static void validate(String name, String password, String password_2, String mail) throws SingUpException {
 		if(!ValidationUtils.validateUsername(name)) {
 			throw new SingUpException("Invalid username");
 		}
 		
 		if(!ValidationUtils.validatePassword(password)) {
 			throw new SingUpException("Invalid password");
 		}
 		
 		if(!password.equals(password_2)) {
 			throw new SingUpException("Passwords must be the same");
 		}
 		
 		if(mail != null) {
 			if(!EmailValidator.getInstance().isValid(mail)) {
 	 			throw new SingUpException("Invalid email");
 	 		}
 		}
 		
 	}
 	
 	private Usuario findByName(String name) {
 		
 		Optional<Usuario> req = usuarioRepository.findByName(name);
 		
 		if (req.isPresent()) {
			return req.get();
		}else {
			throw new UsernameNotFoundException("No existe usuario con dicho nombre");
		}
 		
 	}

}
