package com.example.mitimiti.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
	
	@RequestMapping("/error")
	public String error(HttpServletRequest httpRequest, ModelMap model) {
		
		int errorCode = this.getErrorCode(httpRequest);
		String errorMessage = "Ups.. ";
		
		switch (errorCode) {
			case 400:
					errorMessage = errorMessage.concat("Bad Request");
				break;
			case 401:
					errorMessage = errorMessage.concat("Unauthorized");
				break;
			case 403:
					errorMessage = errorMessage.concat("Forbidden");
				break;
			case 404:
					errorMessage = errorMessage.concat("Resource not found");
				break;
			case 500:
					errorMessage = errorMessage.concat("Internal Server Error");
				break;
			case 502:
					errorMessage = errorMessage.concat("Bad Gateway");
				break;
			case 503:
					errorMessage = errorMessage.concat("Service Unavailable");
				break;
			default:
					errorMessage = errorMessage.concat("Something went wrong");
				break;
		}
		
		model.addAttribute("code", errorCode);
		model.addAttribute("message", errorMessage);
		
		return "error";
	}
	
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    } 

}
