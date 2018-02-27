package com.revature.utility;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.revature.accounts.CustomerAccount;

public class Testing {
	
//	public static void main(String[] args) {
//		CustomerAccount customer = new CustomerAccount();
//		CustomerAccount customer1 = new CustomerAccount();
//		CustomerAccount customer2 = new CustomerAccount();
//		CustomerAccount customer3 = new CustomerAccount();
//		
//		ArrayList<CustomerAccount> customers = new ArrayList<CustomerAccount>();
//		customers.add(customer);
//		customers.add(customer1);
//		customers.add(customer2);
//		customers.add(customer3);
//		
//		testCustomerAddBalance(customers);
//		
//	}
	
	CustomerAccount customer = new CustomerAccount();
	
	@Test
	public void testCustomerAddBalance() {		

		double balance = customer.getTotalBalance();
		customer.depositChecking(10);
		assertTrue(customer.getTotalBalance() > balance);
		

		balance = customer.getTotalBalance();
		customer.depositChecking(-1);
		assertTrue(customer.getTotalBalance() == balance);
		
		

		balance = customer.getTotalBalance();
		customer.depositSavings(1);
		assertTrue(customer.getTotalBalance() > balance);
		
		
		balance = customer.getTotalBalance();
		customer.depositSavings(-4);
		assertTrue(customer.getTotalBalance() == balance);
		
	}
	
	public void withdrawTests() {		

		double balance = customer.getTotalBalance();
		customer.withdrawSavings(1);
		assertTrue(customer.getTotalBalance() < balance);
		assertTrue(customer.getSavingsBalance() < balance);
		
		balance = customer.getTotalBalance();
		customer.withdrawSavings(-1);
		assertTrue(customer.getTotalBalance() == balance);
		assertTrue(customer.getSavingsBalance() == balance);
		
		balance = customer.getTotalBalance();
		customer.withdrawChecking(1);
		assertTrue(customer.getTotalBalance() < balance);
		assertTrue(customer.getSavingsBalance() < balance);
		
		balance = customer.getTotalBalance();
		customer.withdrawChecking(-1);
		assertTrue(customer.getTotalBalance() == balance);
		assertTrue(customer.getCheckingBalance() == balance);
		
	}

}
