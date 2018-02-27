package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransactionDAOClass implements TransactionDAO {
	private Connection conn;

	@Override
	public ResultSet readTransactionListForUser(int user_id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("select * from transactions where user_id = ?");
		ps.setInt(1, user_id);
		
		return ps.executeQuery();
	}

	@Override
	public void insertTransaction(int user_id, String description) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("Insert into transactions values (?,?,?)");
		ps.setInt(1, user_id);
		String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		ps.setString(2, timestamp);
		ps.setString(3, description);
		ps.executeQuery();  
	}
	
	public TransactionDAOClass(Connection conn){
		this.conn = conn;
	}
}
