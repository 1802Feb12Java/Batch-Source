package com.revature.testing;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.revature.bankingappnew.Account;
import com.revature.bankingappnew.Admin;

public class AdminTest {
	
	private Admin norma;
	private Account a;
	
	@Before
	public void setup() throws SQLException {
		norma = com.revature.bankingappnew.daoimplementations.UserLogImp.getAdminObject("nlb1");
		a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(2001);
		norma.deposit(a, 500);
	}
	
	
	@Test
	public void testCloseAccount() throws SQLException {
		norma.changeAccountStatus(a, "Closed");
		assertTrue(a.getAccountStatus().equals("Closed"));
	}
	
	@Test
	public void testReOpenAccount() throws SQLException {
		norma.changeAccountStatus(a, "Open");
		assertTrue(a.getAccountStatus().equals("Open"));
	}

	@Test
	public void testDeposit() throws SQLException {
		double initialBalance = a.getBalance();
		norma.deposit(a, 15.00);
		assertTrue(a.getBalance() > initialBalance);
	}

	@Test
	public void testWithdraw() throws SQLException {
		double initialBalance = a.getBalance();
		norma.withdraw(a, 1.00);
		assertTrue(a.getBalance() < initialBalance);
	}


}
