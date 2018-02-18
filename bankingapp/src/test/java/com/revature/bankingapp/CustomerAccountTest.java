package com.revature.bankingapp;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class CustomerAccountTest {

	@Test
	public void testRegistration() {
		//create and save a new account
		CustomerAccount customerAccount = new CustomerAccount();
		String testFirstName = "John";
		String testLastName = "Smith";
		String testUsername = "JSmith";
		String testPassword = "123abc";
		customerAccount.registerCustomer(testFirstName, testLastName, testUsername, testPassword);
		File file = new File("./" + testUsername);
		assertEquals(true, file.exists());
	}
	

}
