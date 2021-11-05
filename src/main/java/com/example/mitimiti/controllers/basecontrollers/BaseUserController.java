package com.example.mitimiti.controllers.basecontrollers;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.mitimiti.entities.Usuario;
import com.example.mitimiti.util.ErrorHandler;
import com.example.mitimiti.util.exceptions.SessionException;

public abstract class BaseUserController implements ErrorHandler{
	
	protected final String REDIRECT_TO_LOGIN = "redirect:/?action=login";
	protected final String REDIRECT_TO_PANEL = "redirect:/user";
	protected final String REDIRECT_TO_EVENT = "redirect:/user/evento";
	
	protected Usuario obtainLoggedUser() throws SessionException {
		
 		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

 		HttpSession session = attr.getRequest().getSession(false);
 		
 		Usuario sessionUsuario = (Usuario) session.getAttribute("clientesession");
 		
 		if (sessionUsuario != null) {
			return sessionUsuario;
		}else {
			throw new SessionException("No user session");
		}

	}

}
