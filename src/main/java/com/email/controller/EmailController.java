package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.service.EmailService;

@RestController
@CrossOrigin
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to welcome page";
	}
	
	//api to send email
	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		System.out.println("data: "+request );
		boolean result = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo()); 
		
		if(result) {
			return ResponseEntity.ok("Email is Sent Successfully..");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Not Sent...");
		}
		
		
		
	}
	

}
