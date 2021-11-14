package com.example.mitimiti.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.mitimiti.entities.Participant;

@Service
public class SendService {

	@Autowired(required = true)
	private JavaMailSender javaMailSender;
	
	@Async
	public void sayHello(String name, Optional<String> mail) {
		
		if (mail.isPresent()) {
		
			SimpleMailMessage email = new SimpleMailMessage();
			String mensaje = "Hola ".concat(name).concat("! Bienvenido a mitimiti app\n\nTus amigos te esperan! \n\nIngresá a la app desde: http://localhost:8080/?action=login");
			
			email.setTo(mail.get());
			email.setFrom("mitimiti.app@gmail.com");
			email.setSubject("Bienvenido a mitimiti!");
			email.setText(mensaje);
			
			javaMailSender.send(email);
		}
		
	}

	@Async
	public void sendResume(String eventName, HashMap<Participant, HashMap<String, Double>> resume) throws Exception {
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("mitimiti.app@gmail.com");
		email.setSubject("Resumen de tu evento!");
		
		String mensaje = 	"Resumen de saldos de: ".concat(eventName).concat("\n\n")
							.concat("-------------------------------").concat("\n");
		
		
		for (Map.Entry<Participant, HashMap<String, Double>> detail : resume.entrySet()) {
			
			Participant participant = detail.getKey();
			HashMap<String, Double> expenseResumen = detail.getValue();
			
			String balance = String.valueOf(expenseResumen.get("balance"));
			
			mensaje = mensaje.concat("|".concat(participant.getName()).concat("   -> ").concat(" $ ").concat(balance).concat("\n"));
			
		}
		
		mensaje = mensaje.concat("-------------------------------").concat("\n\n");
		
		
		for (Map.Entry<Participant, HashMap<String, Double>> participantDetail : resume.entrySet()) {
			Participant participant = participantDetail.getKey();

			email.setTo(participant.getMail());
			email.setText(mensaje);
			
			javaMailSender.send(email);
			
		}
		
	}
}
