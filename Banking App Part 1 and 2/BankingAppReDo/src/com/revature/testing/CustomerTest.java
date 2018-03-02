package com.revature.testing;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.revature.bankingappnew.Account;
import com.revature.bankingappnew.Customer;

public class CustomerTest {
	private Customer ja;
	private Account dyl_a;
	private Account ja_a;
	
	@Before
	public void setup() throws SQLException {
		ja = com.revature.bankingappnew.daoimplementations.UserLogImp.getCustomerObject("jAbernathy");
		dyl_a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(2002);
		ja_a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(2000);
		
	}
	
	
	@Test
	public void testDeposit() throws SQLException {
		double initialBalance = ja_a.getBalance();
		ja.deposit(ja_a, 1.00);
		ja_a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(2000);
		assertTrue(initialBalance < ja_a.getBalance());
		
	}

	@Test
	public void testnegDeposit() throws SQLException{
		double initialBalance = ja_a.getBalance();
		ja.deposit(ja_a,  -10);
		assertTrue(initialBalance == ja_a.getBalance());
	}
	
	@Test
	public void testWithdraw() throws SQLException {
		double initialBalance = ja_a.getBalance();
		ja.withdraw(ja_a, 1.00);
		assertTrue(initialBalance > ja_a.getBalance());
				
	}
	
	@Test
	public void testOverDraw()throws SQLException{
		double initialBalance = ja_a.getBalance();
		ja.withdraw(ja_a, 10000000);
		assertTrue(initialBalance == ja_a.getBalance());
	}

	@Test
	public void testTransfer() throws SQLException {
		double initialBalance = ja_a.getBalance();
		double initialBalance2 = dyl_a.getBalance();
		ja.transfer(ja_a, dyl_a, 1.00);
		assertTrue(initialBalance > ja_a.getBalance() && initialBalance2 < dyl_a.getBalance());
		
	}
	
	@Test
	public void testInvalidTransfer() throws SQLException {
		double initialBalance = ja_a.getBalance();
		double initialBalance2 = dyl_a.getBalance();
		ja.transfer(dyl_a, ja_a, 1.00);
		assertTrue(initialBalance == ja_a.getBalance() && initialBalance2 == dyl_a.getBalance());
		
	}

}
