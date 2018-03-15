package com.revature.services.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class EmailServletA
 */
public class EmailServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(EmailServlet.class);
	private static final String SENDER_EMAIL = "jrufox@gmail.com";
	private static final long serialVersionUID = 1L;

	public EmailServlet() {
		super();
	}

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * Gets a post request to email a user either about a new account
	 * 
	 * or email their request got updated
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Received a POST Request");

		// get the JSON from req
		// logger.debug("content type=" + request.getContentType());
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			logger.error(e);
		}

		// // PARAM APPROACH
		String req = jb.toString();
		String emailTo = req.substring(req.indexOf("emailTo"), req.indexOf("&"));
		String emailBody = req.substring(req.indexOf("emailBody"), req.length());

		logger.debug(emailTo);
		logger.debug(emailBody);
		// try {
		// jsonObject = HTTP.toJSONObject(jb.toString());
		// } catch (JSONException e) {
		// logger.error("Error parsing JSON request string");
		// }
		// logger.debug("got json from req " + jsonObject);
		// logger.debug(jsonObject.keys().next());
		// get to: and body from passed JSON
		// String emailTo = jsonObject.getString("emailTo");
		//
		// String emailBody = jsonObject.getString("emailBody");

		// // get user from db
		// int userId = jsonObject.getInt("userId");
		// User userSending = Users.getUser(userId);
		// if (userSending == null) {
		// logger.error("No user with id " + userId + " found");
		// response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		// return;
		// }
		// logger.debug("Got user to email " + userSending);

		///// Email code from
		///// https://www.tutorialspoint.com/java/java_sending_email.html
		// String to = userSending.getEmail();

		String from = SENDER_EMAIL;
		logger.debug("Sending email from " + SENDER_EMAIL);

		// sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Now set the actual message
			message.setText(emailBody);

			// Send message
			Transport.send(message);
			logger.info("Sent message successfully....");
		} catch (MessagingException mex) {
			logger.error(mex);
		}

	}

}
