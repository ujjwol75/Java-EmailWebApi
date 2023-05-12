package com.email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerResponse.SseBuilder;



@Service
public class EmailService {

	public boolean sendEmail(String subject, String message, String to) {
		
		boolean f = false;
		
		String from = "tomisbrainy840@gmail.com";
		
		//variable for gmail host
		String gmailHost = "smtp.gmail.com";
		
		
		//get the system properties
		Properties properties = System.getProperties();
//		System.out.println(properties);
		
		//setting important information to properties object
		
		// host 
		properties.put("mail.smtp.host", gmailHost);
		//port
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//step:1 to get the session object
		
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				// Google ko app password maa gayera yeuta custom app generate garni ani password lekhni
				return new PasswordAuthentication("tomisbrainy840@gmail.com", "kcqvzdrqyoqmtgwj");
				
			}
		});
		
		session.setDebug(true);
		
		//step:2 Compost the message [text, multimedia etc.]
		MimeMessage m = new MimeMessage(session);
		
		
		
		try {
			//from email
			m.setFrom(from);
			
			//adding recipient
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			m.setSubject(subject);
			
			//adding text to message
			m.setText(message);
			
			//step-3 : Send the message using Transport class
			Transport.send(m);
			
			System.out.println("Sent Success.................");
			f = true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return f;
		
		
		
		
		
	}
	
}
