package com.got.service;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Inject private JavaMailSender sender;
	
	public void send(String title, String content, String emailAddr) {
		MimeMessage message = sender.createMimeMessage();
		try {
			message.setFrom(new InternetAddress("jyc228@naver.com"));
			message.setSubject(title);
			message.setText(content);
			
				message.setRecipient(RecipientType.TO, new InternetAddress(emailAddr));
			sender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
