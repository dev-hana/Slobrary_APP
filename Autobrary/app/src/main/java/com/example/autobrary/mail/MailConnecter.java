package com.example.autobrary.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailConnecter extends Thread {
	private String protocol = "smtp";
	private String type = "text/html; charset=KSC5601";
	private String userName = "no-reply";   
	private String password = "legoslo2020";   
	private String host = "slobrary.com";
	private int port = 25;	//25
	private boolean starttlsEnable = false;
	private String subject = "";
	private String contents = "";
	private String fromEmail = "";
	private String fromName = "";
	ArrayList<HashMap<String, String>> list;

	public MailConnecter(String subject, String contents, String fromEmail, String fromName, ArrayList<HashMap<String, String>> list){
		this.subject = subject;
		this.contents = contents;
		this.fromEmail = fromEmail;
		this.fromName = fromName;
		this.list = list;
	}
	
	public MailConnecter(String subject, String contents, ArrayList<HashMap<String, String>> list){
		this.subject = subject;
		this.contents = contents;
		this.fromEmail = "no-reply@slobrary.com";
		this.fromName = "SL:O Brary";
		this.list = list;
	}

	public void run() {
		HashMap<String, String> map = null;
		for(int i=0, j=list.size(); i<j; i++){
			map = list.get(i);
			try {
				Thread.sleep(100);
				send(map.get("email"), map.get("name"), fromEmail, fromName, subject, contents);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void send(String toEmail, String toName, String fromEmail, String fromName, String subject, String content) throws Exception {
		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", protocol);
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", String.valueOf(port));
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", Boolean.toString(starttlsEnable));	
	
			Authenticator authenticator = new SMTPAuthenticator(userName, password);
			Session session = Session.getInstance(props, authenticator);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail, fromName));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, toName));
			message.setSubject(subject);
			message.setContent(content, type);
			Transport.send(message);
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		} catch (MessagingException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	class SMTPAuthenticator extends Authenticator {
		PasswordAuthentication passwordAuthentication;
		SMTPAuthenticator(String userName, String password) {
			passwordAuthentication = new PasswordAuthentication(userName, password);
		}
		public PasswordAuthentication getPasswordAuthentication() {
			return passwordAuthentication;
		}
	}

}