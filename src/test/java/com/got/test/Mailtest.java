package com.got.test;

import java.util.Arrays;

import javax.inject.Inject;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.helper.TestUtil;
import com.got.service.MailService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class Mailtest {
	
	@Inject MailService mailService;
	@Inject private JavaMailSender sender;
	
	@Test
	public void send() {
		TestUtil.printMethod("test");
	}
	
	@Test
	public void createAddr() throws AddressException {
		TestUtil.printMethod("createAddr");
	}
}
