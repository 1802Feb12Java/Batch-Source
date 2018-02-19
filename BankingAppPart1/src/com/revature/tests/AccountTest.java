package com.revature.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.revature.Account;
import com.revature.Customer;

public class AccountTest {
	private Account a1;
	private Account a2;
	
	@Before
	public void setup() {
		a1 = new Account(new Customer("user1", "pass1", "name1"), new Customer("u2", "p2", "n2"));
		a2 = new Account();
		a1.setApproved(true);
		a2.setApproved(true);
		a1.setBalance(500.0);
	}
	
	@Test
	public void testAccountSetsUpCorrectly() {
		assertTrue(a1.getAccountID() != a2.getAccountID());	//apparently Assert.assertEquals is depreciated, yaaaaaaaaaaaaay
		assertTrue(a1.getCustomers().size() == 2);
		assertTrue(500.0 == a1.getBalance());
		assertTrue(0.0 == a2.getBalance());
	}
	
	@Test
	public void testWithdrawReturnsFalseWhenAmountGreaterThanBalance() {
		assertFalse(a1.withdraw(600));
		assertTrue(500.0 == a1.getBalance());
	}

	@Test
	public void testWithdrawReturnsFalseWhenAmountLessThanZero() {
		assertFalse(a1.withdraw(-4395));
		assertTrue(500.0 == a1.getBalance());
	}
	
	@Test
	public void testWithdrawUpdatesBalanceWhenAllCorrect() {
		assertTrue(a1.withdraw(300));
		assertTrue(200.0 == a1.getBalance());
	}
	
	@Test
	public void testDepositReturnsFalseWhenAmountLessThanZero() {
		assertFalse(a1.deposit(-2));
		assertTrue(500.0 == a1.getBalance());
	}
	
	@Test
	public void testDepositReturnsFalseWhenDepositingMoreThanMaxValue() {
		assertFalse(a1.deposit(Double.MAX_VALUE));
		assertTrue(500.0 == a1.getBalance());
	}
	
	@Test
	public void testDepositUpdatesBalanceWhenAllCorrect() {
		assertTrue(a1.deposit(300));
		assertTrue(800.0 == a1.getBalance());
	}
	
	@Test
	public void testTransferReturnsFalseWhenReceiverIsNull() {
		assertFalse(a1.transfer(null, 200));
		assertTrue(500.0 == a1.getBalance());
		assertTrue(0.0 == a2.getBalance());
	}
	
	@Test
	public void testTransferReturnsFalseWhenCallingAccountIsNotApproved() {
		a1.setApproved(false);
		assertFalse(a1.transfer(a2, 200));
		assertTrue(500.0 == a1.getBalance());
		assertTrue(0.0 == a2.getBalance());
	}
	
	@Test
	public void testTransferReturnsFalseWhenReceiverIsNotApproved() {
		a2.setApproved(false);
		assertFalse(a1.transfer(a2, 200));
		assertTrue(500.0 == a1.getBalance());
		assertTrue(0.0 == a2.getBalance());
	}
	
	@Test
	public void testTransferReturnsFalseWhenMoneyTooLow() {
		assertFalse(a1.transfer(a2, 600));
		assertTrue(500.0 == a1.getBalance());
		assertTrue(0.0 == a2.getBalance());
	}
	
	@Test
	public void testTransferReturnsFalseWhenNegativeNumberInput() {
		assertFalse(a1.transfer(a2, -2));
		assertTrue(500.0 == a1.getBalance());
		assertTrue(0.0 == a2.getBalance());
	}
	
	@Test
	public void testTransferTransfersCorrectly() {
		assertTrue(a1.transfer(a2, 200));
		assertTrue(300.0 == a1.getBalance());
		assertTrue(200.0 == a2.getBalance());
	}
}
