# Project 1 - Employee Reimbursement System

## To run:

Click [here](http://josephjustn-ec2.ddns.net/Project1/) to go to the DNS hostname for the web application.

## Testing accounts

For employee account login, can change information via administrator account

Adminstrator Login
### Username: admin
### Password: passw0rd

## JUnit Testing

Uncomment

```Java
import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.Test;

import com.revature.controllers.Manager;
import com.revature.controllers.User;
import com.revature.dao.implementation.ConnFactory;
```

and 

```Java
	@Test
	public void testConnectionToDatabase() throws SQLException {
		try {
			assertNotNull(ConnFactory.getInstance().getConnection());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetUserInformation() throws SQLException {
		assertTrue(User.viewFirstName("admin").equals("Michael"));
		assertNotNull(User.viewUserInformation("admin"));
		assertTrue(User.getRole("admin").equals("manager"));
	}

	@Test
	public void testLogin() throws SQLException {
		assertTrue(User.logIn("admin", "passw0rd"));
		assertFalse(User.logIn("admin", "p1"));
	}

	@Test
	public void testGetReimbursements() throws SQLException {
		assertNotNull(Manager.viewAll());
	}

	@Test
	public void testGetUserReimbursements() throws SQLException {
		assertNotNull(User.viewReimbursementRequests("admin"));
	}

	@Test
	public void testDataRetrieval() throws SQLException {
		assertNotNull(Manager.totalCostReimbursments());
		assertNotNull(Manager.totalApprovedReimbursments());
	}
```

from [Project1Test.java in src/test/](src/test/java/com/revature/test/Project1Test.java)