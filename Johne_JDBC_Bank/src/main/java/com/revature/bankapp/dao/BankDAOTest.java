package com.revature.bankapp.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.revature.bankapp.controller.BankController;
import com.revature.bankapp.model.BankAccount;

public class BankDAOTest {

	private BankAccount a;
	private BankAccount b;
	
	@Before
	public void setup() {
		a = new BankAccount();
		a.setAccountID(1001);
		a.setCustomerID(1001);
		a.setBalance(0);
		
		b = new BankAccount();
		b.setAccountID(1002);
		b.setCustomerID(1002);
		b.setBalance(500);
	}
	
	@Test
	public void testDeposit() throws SQLException {
		BankController.deposit(500, a);
		assertTrue(a.getBalance() == b.getBalance());	//test to see if both account has same balance
	}

	@Test
	public void testWithdraw() throws SQLException {
		BankController.withdraw(500, b);
		assertTrue(b.getBalance() == a.getBalance());	//test to see if both account balance are zero
	}

}
