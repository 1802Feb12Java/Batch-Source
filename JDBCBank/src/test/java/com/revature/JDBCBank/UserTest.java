package com.revature.JDBCBank;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.revature.JDBCBank.Account;
import com.revature.JDBCBank.User;

public class UserTest {
	private User u1;
	private User u2;
	private Account a1;
	
	@Before
	public void setup() {
		u1 = new User("username1", "password1", "name1");
		u2 = new User("u2", "p2", "n2");
		a1 = new Account();
		u1.addAccount(a1);
	}
	
	@Test
	public void testUserConstructedCorrectly() {
		assertTrue(u1.getUserID() != u2.getUserID());
		assertTrue(u1.getAccounts().size() == 1);
		assertTrue(u2.getAccounts().size() == 0);
		assertTrue(u1.getUsername() == "username1" && u1.getPassword() == "password1" && u1.getFullName() == "name1");
		assertTrue(u2.getUsername() == "u2" && u2.getPassword() == "p2" && u2.getFullName() == "n2");
	}

	@Test
	public void testAddAccountAddsToBoth() {
		assertFalse(u2.getAccounts().contains(a1));
		assertFalse(a1.getUsers().contains(u2));
		u2.addAccount(a1);
		assertTrue(u2.getAccounts().contains(a1));
		assertTrue(a1.getUsers().contains(u2));
	}
	
	@Test
	public void testRemoveAccountRemovesFromBoth() {
		assertTrue(u1.getAccounts().contains(a1));
		assertTrue(a1.getUsers().contains(u1));
		u1.removeAccount(a1);
		assertFalse(u1.getAccounts().contains(a1));
		assertFalse(a1.getUsers().contains(u1));
	}
	
	@Test
	public void testRemoveAccountChecksUsersAccountList() {
		assertTrue(a1.getUsers().contains(u1));
		int i = u1.getAccounts().indexOf(a1);
		u1.getAccounts().remove(i);
		assertFalse(u1.getAccounts().contains(a1));
		assertFalse(u1.removeAccount(a1));
	}
	
	@Test
	public void testRemoveAccountChecksAccountsUserList() {
		assertTrue(u1.getAccounts().contains(a1));
		assertTrue(a1.getUsers().contains(u1));
		int i = a1.getUsers().indexOf(u1);
		a1.getUsers().remove(i);
		assertFalse(u1.removeAccount(a1));
	}
}
