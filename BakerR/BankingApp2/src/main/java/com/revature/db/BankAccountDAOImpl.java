package com.revature.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.BankAccount;
import com.revature.model.Customer;

public class BankAccountDAOImpl implements BankAccountDAO {

	private Connection conn;
	
	public BankAccountDAOImpl() throws ClassNotFoundException, SQLException {
		conn = ConnectionManager.getInstance().getConnection();
	}
	
	@Override
	public BankAccount createAccount(Customer owner) throws SQLException {
		int acctId = -1;
		String query = "{call REGISTER_ACCOUNT[(?, ?)]}";
		// ACCTID OUT NUMBER, OWNERID IN NUMBER
		CallableStatement cs = conn.prepareCall(query);
		cs.registerOutParameter(1, Types.NUMERIC);
		cs.setInt(2, owner.getUserId());
		
		cs.executeUpdate();
		
		acctId = cs.getInt(1);
		
		cs.close();
		
		return getAccount(acctId);
	}
	
	
	@Override
	public BankAccount getAccount(int acctId) throws SQLException {
		BankAccount acct;
//		--    ACCOUNTID NUMBER PRIMARY KEY,
//		--    ACTIVE NUMBER,
//		--    OWNER NUMBER,
//		--    BALANCE NUMBER(38, 2)
		String query = "SELECT * FROM ACCOUNTS WHERE ACCOUNTID=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, acctId);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			acct = new BankAccount();
			acct.setAccountId(rs.getInt("ACCOUNTID"));
			acct.setActive(rs.getInt("ACTIVE") == 1);
			acct.setOwner(rs.getInt("OWNER"));
			acct.setBalance(rs.getDouble("BALANCE"));
		} else {
			acct = null;
		}
		
		ps.close();
		
		return acct;
	}

	@Override
	public double getBalance(BankAccount acct) throws SQLException {
		double balance;
		String query = "SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNTID=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, acct.getAccountId());
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		balance = rs.getDouble("BALANCE");
		ps.close();
		return balance;
	}

	@Override
	public List<BankAccount> getAllAccounts() throws SQLException {
//		--    ACCOUNTID NUMBER PRIMARY KEY,
//		--    ACTIVE NUMBER,
//		--    OWNER NUMBER,
//		--    BALANCE NUMBER(38, 2)
		List<BankAccount> acctList = new ArrayList<>();
		String query = "SELECT * FROM ACCOUNTS";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			BankAccount acct = new BankAccount();
			acct.setAccountId(rs.getInt("ACCOUNTID"));
			acct.setActive(rs.getInt("ACTIVE") == 1);
			acct.setBalance(rs.getDouble("BALANCE"));
			acct.setOwner(rs.getInt("OWNER"));
			
			acctList.add(acct);
		}
		
		ps.close();
		return acctList;
	}

	@Override
	public List<BankAccount> getAllUserAccounts(Customer owner) throws SQLException {
		List<BankAccount> acctList = new ArrayList<>();
		String query = "SELECT * FROM ACCOUNTS WHERE OWNER=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, owner.getUserId());
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			BankAccount acct = new BankAccount();
			acct.setAccountId(rs.getInt("ACCOUNTID"));
			acct.setActive(rs.getInt("ACTIVE") == 1);
			acct.setBalance(rs.getDouble("BALANCE"));
			acct.setOwner(rs.getInt("OWNER"));
			
			acctList.add(acct);
		}
		
		return acctList;
	}

	@Override
	public void activateAccount(BankAccount acct) throws SQLException {
		String query = "UPDATE ACCOUNTS SET ACTIVE=1 WHERE ACCOUNTID=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, acct.getAccountId());
		
		ps.executeUpdate();
		ps.close();
		acct.setActive(true);
	}

	@Override
	public void deactivateAccount(BankAccount acct) throws SQLException {
		String query = "UPDATE ACCOUNTS SET ACTIVE=0 WHERE ACCOUNTID=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, acct.getAccountId());
		
		ps.executeUpdate();
		ps.close();
		acct.setActive(false);
		
	}

	@Override
	public void updateAccount(BankAccount acct) throws SQLException {
		String query = "UPDATE ACCOUNTS SET BALANCE=? WHERE ACCOUNTID=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setDouble(1, acct.getBalance());
		ps.setInt(2, acct.getAccountId());
		
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void withdraw(BankAccount acct, double amount) throws SQLException {
		String query = "{call WITHDRAW[(?, ?)]}"; // ID IN NUM, VAL IN NUM
		CallableStatement cs = conn.prepareCall(query);
		cs.setInt(1, acct.getAccountId());
		cs.setDouble(2, amount);
		
		cs.executeUpdate();
		
		cs.close();
	}

	@Override
	public void deposit(BankAccount acct, double amount) throws SQLException {
		String query = "{call DEPOSIT[(?, ?)]}"; // ID IN NUM, VAL IN NUM
		CallableStatement cs = conn.prepareCall(query);
		cs.setInt(1, acct.getAccountId());
		cs.setDouble(2, amount);
		
		cs.executeUpdate();
		
		cs.close();
	}

	@Override
	public void transfer(BankAccount to, BankAccount from, double amount) throws SQLException {
		String query = "{call TRANSFER[(?, ?, ?)]}"; // FROM, TO, AMT
		CallableStatement cs = conn.prepareCall(query);
		cs.setInt(1, from.getAccountId());
		cs.setInt(2, to.getAccountId());
		cs.setDouble(3, amount);
		
		cs.executeUpdate();
		
		cs.close();
	}

}
