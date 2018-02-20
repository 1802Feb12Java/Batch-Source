package com.revature.managers;

import com.revature.model.Account;
import com.revature.model.Customer;

/**
 * Utility class provides static methods for [un]linking data.
 */
public final class Linker {
	public static void linkCustomerAndAccount(Customer c, Account a) {
		linkCustomerToAccount(c, a);
		linkAccountToCustomer(c, a);
	}
	
	public static void linkCustomerToAccount(Customer c, Account a) {
		a.getOwnerIds().add(c.getId());
	}
	
	public static void linkAccountToCustomer(Customer c, Account a) {
		c.getAccountIds().add(a.getId());
	}
	
	public static void unlinkCustomerAndAccount(Customer c, Account a) {
		unlinkCustomerFromAccount(c, a);
		unlinkAccountFromCustomer(c, a);
	}
	
	public static void unlinkCustomerFromAccount(Customer c, Account a) {
		a.getOwnerIds().remove(c.getId());
	}
	
	public static void unlinkAccountFromCustomer(Customer c, Account a) {
		c.getAccountIds().remove(a.getId());
	}
}
