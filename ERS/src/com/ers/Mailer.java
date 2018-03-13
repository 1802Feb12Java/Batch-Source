package com.ers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

	public static void SendMail(String recipientEmail, String subject, String emailText) {


		String from = "ERS@localhost.localdomain";
		String host = "localhost";
		Properties properties = System.getProperties();

		properties.setProperty("mail.smtp.host", host);

		Session session = Session.getDefaultInstance(properties);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setSubject(subject);
			message.setText(emailText);
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
