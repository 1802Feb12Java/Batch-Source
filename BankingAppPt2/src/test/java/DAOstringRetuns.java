import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.revature.bankingappPT2.UserDAObject;
import com.revature.bankingappPT2.UserDriver;

public class DAOstringRetuns {
	
	
	
	@Test
	public void test() throws SQLException {
		UserDAObject dao = new UserDAObject();
		String output = dao.getAccountDetails("John");
		String[] outputs = output.split("::");
		for (String str: outputs) {
			//System.out.println(str);
			if(str.contains("Balance")) {
				outputs = str.split(":");
				System.out.println(outputs[1].trim().replace("$", ""));
				
				//System.out.println(outputs[1].trim());
			}
		}
	}

}
