package com.revature.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.revature.Account;
import com.revature.Customer;

public class CustomerTest {
	private Customer c1;
	private Customer c2;
	private Account a1;
	
	@Before
	public void setup() {
		c1 = new Customer("username1", "password1", "name1");
		c2 = new Customer("u2", "p2", "n2");
		a1 = new Account();
		c1.addAccount(a1);
	}
	
	@Test
	public void testCustomerConstructedCorrectly() {
		assertTrue(c1.getCustomerID() != c2.getCustomerID());
		assertTrue(c1.getAccounts().size() == 1);
		assertTrue(c2.getAccounts().size() == 0);
		assertTrue(c1.getUsername() == "username1" && c1.getPassword() == "password1" && c1.getFullName() == "name1");
		assertTrue(c2.getUsername() == "u2" && c2.getPassword() == "p2" && c2.getFullName() == "n2");
	}

	@Test
	public void testAddAccountAddsToBoth() {
		assertFalse(c2.getAccounts().contains(a1));
		assertFalse(a1.getCustomers().contains(c2));
		c2.addAccount(a1);
		assertTrue(c2.getAccounts().contains(a1));
		assertTrue(a1.getCustomers().contains(c2));
	}
	
	@Test
	public void testRemoveAccountRemovesFromBoth() {
		assertTrue(c1.getAccounts().contains(a1));
		assertTrue(a1.getCustomers().contains(c1));
		c1.removeAccount(a1);
		assertFalse(c1.getAccounts().contains(a1));
		assertFalse(a1.getCustomers().contains(c1));
	}
	
	@Test
	public void testRemoveAccountChecksCustomersAccountList() {
		assertTrue(a1.getCustomers().contains(c1));
		int i = c1.getAccounts().indexOf(a1);
		c1.getAccounts().remove(i);
		assertFalse(c1.getAccounts().contains(a1));
		assertFalse(c1.removeAccount(a1));
	}
	
	@Test
	public void testRemoveAccountChecksAccountsCustomerList() {
		assertTrue(c1.getAccounts().contains(a1));
		assertTrue(a1.getCustomers().contains(c1));
		int i = a1.getCustomers().indexOf(c1);
		a1.getCustomers().remove(i);
		assertFalse(c1.removeAccount(a1));
	}
}
