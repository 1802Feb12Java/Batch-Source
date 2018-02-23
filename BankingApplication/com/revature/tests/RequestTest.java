package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.banking.Request;
import com.revature.banking.Requests;
import com.revature.banking.people.Customer;

public class RequestTest {

	@Test
	public void testApproveRequest() {
		Customer c = new Customer();
		Request r = new Request("test", c, null);
		Requests.addRequestToFile(r);
		r.approveRequest();

		// verify request deleted
		assertNull(Requests.getRequstById(c.getPersonId()));
		// and person has account
		assertNotNull(c.getBankAccountId());
	}

	@Test
	public void testDenyRequest() {
		Customer c = new Customer();
		Request r = new Request("test", c, null);
		Requests.addRequestToFile(r);
		r.denyRequest();

		// verify request deleted
		assertNull(Requests.getRequstById(c.getPersonId()));
		// and person has account
		assertNull(c.getBankAccountId());
	}

	@Test
	public void testGetUserID() {
		Customer c = new Customer();
		Request r = new Request("test", c, null);

		// verify ids match
		assertEquals(r.getUserID(), c.getPersonId());
	}

	@Test
	public void testGetCustomer() {
		Customer c = new Customer();
		Request r = new Request("test", c, null);

		// verify customer matches
		assertTrue(r.getCustomer().equals(c));
	}

	@Test
	public void testGetJointAccountNumber() {
		Customer c = new Customer();
		Request r = new Request("test", c, null);
		Requests.addRequestToFile(r);

		// verify null account is null
		assertNull(r.getJointAccountNumber());

		r.approveRequest();

		// create second account
		Customer c2 = new Customer();
		Request j = new Request("testJoint", c2, c.getBankAccountId());
		Requests.addRequestToFile(j);

		// verify account id was set
		assertNotNull(j.getJointAccountNumber());

		j.approveRequest();

		// verify requests deleted
		assertNull(Requests.getRequstById(c.getPersonId()));
		assertNull(Requests.getRequstById(c2.getPersonId()));
		// and people has same accounts
		assertNotNull(c.getBankAccountId());
		assertNotNull(c2.getBankAccountId());
		assertEquals(c.getBankAccountId(), c2.getBankAccountId());
	}

}
