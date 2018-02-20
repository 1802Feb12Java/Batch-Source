package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.revature.banking.BankAccount;
import com.revature.banking.BankAccounts;

public class BankAccountTest {

	@Test
	public void testWithdraw() {
		BankAccount ba = new BankAccount(0.0);

		// withdraw from empty account
		assertFalse(ba.withdraw(0.01));

		ba = new BankAccount(120.01);
		// withdraw too much
		assertFalse(ba.withdraw(120.1));

		new BankAccount(500.1);
		// withdraw negative
		assertFalse(ba.withdraw(-120.01));

		new BankAccount(120.1);

		// withdraw nothing
		assertTrue(ba.withdraw(0.0));

		// valid withdrawals
		ba = new BankAccount(50.0);

		assertTrue(ba.withdraw(20.1));

		assertTrue(ba.withdraw(9.9));

		assertTrue(ba.withdraw(19.99));

		// precision problems
		assertTrue(ba.getBalance().compareTo(0.01) == 0);
	}

	@Test
	public void testDeposit() {
		BankAccount ba = new BankAccount(120.01);
		// deposit too much
		assertFalse(ba.deposit(500000000.01));

		ba = new BankAccount(500.1);
		// deposit negative
		assertFalse(ba.deposit(-120.01));

		ba = new BankAccount(120.1);
		// deposit nothing
		assertTrue(ba.deposit(0.0));

		ba = new BankAccount(0.0);
		// valid deposit scenario
		assertTrue(ba.deposit(20.1));

		assertTrue(ba.deposit(9.9));

		assertTrue(ba.deposit(19.99));

		assertTrue(ba.deposit(0.01));

		assertTrue(ba.getBalance().compareTo(50.0) == 0);
	}

	@Test
	public void testTransfer() {
		BankAccount ba1 = new BankAccount(120.01);
		BankAccount ba2 = new BankAccount(0.0);
		// commit test accounts to file
		BankAccounts.addAccountToFile(ba1);
		BankAccounts.addAccountToFile(ba2);

		// transfer too much
		assertFalse(ba1.transfer(200.0, ba2.getAccountId()));
		assertFalse(ba2.transfer(1.0, ba1.getAccountId()));

		// transfer negative
		assertFalse(ba1.transfer(-120.01, ba2.getAccountId()));

		// deposit nothing
		assertTrue(ba1.transfer(0.0, ba2.getAccountId()));

		// valid transfer scenario
		assertTrue(ba1.transfer(20.1, ba2.getAccountId()));

		assertTrue(ba1.transfer(9.9, ba2.getAccountId()));

		assertTrue(ba1.transfer(19.99, ba2.getAccountId()));

		assertTrue(ba1.transfer(0.01, ba2.getAccountId()));

		// System.out.println(ba1.getBalance());
		// System.out.println(ba2.getBalance());
		// assertTrue(ba2.getBalance().compareTo(50.0) == 0);
		// assertTrue(ba2.getBalance().compareTo(0.0) == 0);

	}

	@Test
	public void testGetBalance() {
		BankAccount b = new BankAccount(56.5);
		assertEquals(b.getBalance().compareTo(56.5), 0);
	}

	@Test
	public void testSetBalance() {
		BankAccount b = new BankAccount(0.0);
		b.setBalance(50.54);
		assertEquals(b.getBalance().compareTo(50.54), 0);
	}

	@Test
	public void testCloseAccount() {
		BankAccount b = new BankAccount(0.0);
		b.closeAccount();
		try {
			b.getBalance();
		} catch (NullPointerException e) {
			assertTrue(true);
			return;
		}
		fail();
	}

}
