package com.revature.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.revature.dao.CustomerService;

class DriverTester {

	@Test
	void existTest() {
		CustomerService CusTest = new CustomerService();
		Boolean userexist = CusTest.checkUser("sonam","sonam123");
		assertTrue(userexist);
		
		Boolean anotherexist = CusTest.checkUser("Fake", "notexist");
		assertFalse(anotherexist);
	}
	
	@Test
	void depositTest() {
		CustomerService CusTest = new CustomerService();
		Double first = CusTest.getBal("sonam");
		CusTest.depositCus("sonam", 50);
		assertTrue(CusTest.getBal("sonam") > first);
		Double second = CusTest.getBal("sonam");
		CusTest.depositCus("sonam", -50);
		assertTrue(CusTest.getBal("sonam") == second);
	}
	
	@Test
	void withdrawTest() {
		CustomerService CusTest = new CustomerService();
		Double first = CusTest.getBal("sonam");
		CusTest.withdrawCus("sonam", 50);
		assertTrue(CusTest.getBal("sonam") < first);
		Double second = CusTest.getBal("sonam");
		CusTest.withdrawCus("sonam", -50);
		assertTrue(CusTest.getBal("sonam") == second);
	}
	
	@Test
	void transferTest() {
		CustomerService CusTest = new CustomerService();
		
		Double sonamfirst = CusTest.getBal("sonam");
		Double johnfirst = CusTest.getBal("john");
		CusTest.transferCus("sonam", "john", 100);
		assertTrue(CusTest.getBal("sonam") < sonamfirst);
		assertTrue(CusTest.getBal("john") > johnfirst);
		
		Double sonamsecond = CusTest.getBal("sonam");
		Double johnsecond = CusTest.getBal("john");
		CusTest.transferCus("sonam", "john", -100);
		assertTrue(CusTest.getBal("sonam") == sonamsecond);
		assertTrue(CusTest.getBal("john") == johnsecond);
	}
}
