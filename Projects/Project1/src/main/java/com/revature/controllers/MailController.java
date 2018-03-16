package com.revature.controllers;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class MailController {

	public static ClientResponse SendSimpleMessage(String firstName, String username, String password, String emailAddress) {
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", "key-085149de6bc68240388483ba6d2db0c3"));
		WebResource webResource = client
				.resource("https://api.mailgun.net/v3/justinjoseph.ga/messages");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("from", "Justin Joseph <postmaster@justinjoseph.ga>");
		String emailTo = firstName + " <" + emailAddress + ">";
		formData.add("to", emailTo);
		formData.add("subject", "Employee Reimbursement System - Registration");
		String emailText = "Hello, " + firstName + "!\n\nHere are your credentials.\n\nUsername:\t" + username + "\nPassword:\t" + password + "\n\nFrom,\nJustin Joseph\nProject 1 - Employee Reimbursement System";
		formData.add("text", emailText);
		return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
	}
	
	public static ClientResponse SendForgot(String emailAddress) {
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", "key-085149de6bc68240388483ba6d2db0c3"));
		WebResource webResource = client
				.resource("https://api.mailgun.net/v3/justinjoseph.ga/messages");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("from", "Justin Joseph <postmaster@justinjoseph.ga>");
		String emailTo = "Loser" + " <" + emailAddress + ">";
		formData.add("to", emailTo);
		formData.add("subject", "Employee Reimbursement System - Forgot Password");
		String emailText = "Hello" + "!\n\nSucks. :( \n\nFrom,\nJustin Joseph\nProject 1 - Employee Reimbursement System";
		formData.add("text", emailText);
		return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
	}

}