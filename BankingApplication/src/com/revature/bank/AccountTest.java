package com.revature.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccountTest {

	Account a = new Account();
	

	@Test
	void testWithdraw() {
		double balance = a.getAccountBal();
		a.withdraw(10);
		assertTrue(a.getAccountBal() == balance);
		
		a.deposit(100);
		balance = a.getAccountBal();
		a.withdraw(-10);
		assertTrue(a.getAccountBal() == balance);
		
		a.withdraw(50);
		assertTrue(a.getAccountBal() < balance);
		
		balance = a.getAccountBal();
		a.withdraw(1000);
		assertTrue(a.getAccountBal() == balance);
	}


	@Test
	void testDeposit() {
		double balance = a.getAccountBal();
		a.deposit(10);
		assertTrue(a.getAccountBal() > balance);
		
		balance = a.getAccountBal();
		a.deposit(-10);
		assertTrue(a.getAccountBal() == balance);
		
	}

}
