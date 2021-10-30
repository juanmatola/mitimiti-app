package com.example.mitimiti.services;

import java.util.ArrayList;
import java.util.List;

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

 		Usuario usuario = usuarioRepository.getUserByName(name);

 		if (usuario == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

 		List<GrantedAuthority> permisos = new ArrayList<>();

 		GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + "USER");
 		permisos.add(p1);

 		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

 		HttpSession session = attr.getRequest().getSession(true);
 		session.setAttribute("clientesession", usuario);

 		return new User(usuario.getName(), usuario.getPassword(), permisos);
 	}
 	
 	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Usuario createNewUsuario(String name, String password, String password_2, String mail) throws SingUpException {

 		validate(name, password, password_2, mail);
 		
 		String encryptedKey = new BCryptPasswordEncoder().encode(password);
 		
 		Usuario usuario = new Usuario();
 		usuario.setName(name);
 		usuario.setPassword(encryptedKey);
 		usuario.setMail(mail);

		try {
			usuarioRepository.save(usuario);
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

 	private static void validate(String name, String password, String password_2, String mail) throws SingUpException {
 		if(!ValidationUtils.validateUsername(name)) {
 			throw new SingUpException("invalid username");
 		}
 		
 		if(!ValidationUtils.validatePassword(password)) {
 			throw new SingUpException("invalid password");
 		}
 		
 		if(!password.equals(password_2)) {
 			throw new SingUpException("passwords must be the same");
 		}
 		
 		if(!EmailValidator.getInstance().isValid(mail)) {
 			throw new SingUpException("invalid mail");
 		}
 	}

}
