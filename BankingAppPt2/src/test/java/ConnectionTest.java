import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.bankingappPT2.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

	private Connection conn;
	
	@Test
	public void test() throws SQLException {
		
		ConnectionFactory conn2 = new ConnectionFactory();
		assert(!conn2.getConnection().equals(null));
	}

}
