import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.revature.bankingappPT2.Account;
//import com.revature.bankingappPT2.JointAccount;
import com.revature.bankingappPT2.UserDAObject;
import com.revature.bankingappPT2.UserDriver;

public class AccountTests {

	Account testAccount = new Account("John", "Account 1",11, 567.89D, true, 4);
	
	//@Test
	public void chechApprovaltest() {
		System.out.println("Testing");
		assert(testAccount.isApproved());
	}

	@Test
	public void accountCheck() {
		testAccount.setAccountID(11);
		System.out.println(testAccount.getAccountID());
		System.out.println(testAccount.getBalance());
	}
	
	@Test
	public void checkDepo() throws SQLException {
		UserDAObject dao = new UserDAObject();
		//dao.addAccountToTable(testAccount, 0D);
		System.out.println("Balance: "+testAccount.getBalance()+", AccountID: "+testAccount.getAccountID());
		dao.updateAccountBalance(testAccount.getBalance(), testAccount.getAccountID());
		
		
	}
	
	
}











