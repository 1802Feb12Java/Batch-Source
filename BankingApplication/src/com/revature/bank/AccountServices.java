package com.revature.bank;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountServices implements AccountDAO {

	ConnectionFlexion cf = new ConnectionFlexion();
	Connection conn = cf.getConnection();
	
	@Override
	public void addAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account getAccount(int id) throws SQLException {
		String str = "SELECT * FROM ACCOUNTS WHERE A_ID=?";
		PreparedStatement ps = conn.prepareStatement(str);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
		Account a = new Account();
		a.setCustomerID(rs.getString(2));
		a.setAccountBal(rs.getDouble(4));
		a.setAccountNumber(rs.getString(1));
		return a;
		}
		System.out.println("There is no account under that number.");
		return null;
	}

	@Override
	public void updateAccount(Account a) throws SQLException {
		String str = "{CALL ACCT_ACTION(?,?)}";
		CallableStatement cs = conn.prepareCall(str);
		cs.setInt(1, Integer.parseInt(a.getAccountNumber()));
		cs.setDouble(2, a.getAccountBal());
		cs.execute();
		
	}

	@Override
	public void deleteAccount(Account a) throws SQLException {
		
		
	}

	@Override
	public List<Account> getAllAccounts() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
