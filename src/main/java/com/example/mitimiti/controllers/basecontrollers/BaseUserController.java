package com.example.mitimiti.controllers.basecontrollers;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.mitimiti.entity.Usuario;

public abstract class BaseUserController {
	
	protected String loginPath = "?action=login";
	
	protected Usuario obtainLoggedUser() throws Exception {
		
 		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

 		HttpSession session = attr.getRequest().getSession(false);
 		
 		Usuario sessionUsuario = (Usuario) session.getAttribute("clientesession");
 		
 		if (sessionUsuario != null) {
			return sessionUsuario;
		}else {
			throw new Exception("No user session");
		}

	}

}
