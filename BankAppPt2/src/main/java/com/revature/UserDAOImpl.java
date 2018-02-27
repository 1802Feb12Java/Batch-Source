package com.revature;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.User;

public class UserDAOImpl implements UserDAO{
 
	public void createAcc(String name, String password, double bal) throws SQLException{
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			
			String sql = "INSERT INTO USERACCOUNTS VALUES (USER_SEQUENCE.NEXTVAL,  BANK_SEQUENCE.NEXTVAL,?,?,?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, password);
			stmt.setDouble(3, bal);
			
			
			stmt.executeUpdate();
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	public void viewAcc(User u) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		double money = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT ACCOUNTBAL FROM USERACCOUNTS WHERE ACCOUNTID = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, u.getAccountID());

			rs = ps.executeQuery();
;
			while(rs.next()) {
				money = rs.getDouble("ACCOUNTBAL");
			}
			
				
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("The balance in your account is $"+ money);
		
	}
	
	public void deleteAcc(User u){
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM USERACCOUNTS WHERE USERID = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, u.getUserID());
			
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public void depositMoney(User u,  double d) throws SQLException{
		Connection connection = null;
		CallableStatement cs = null;
		
		double newtotal = u.getAccountBal() + d;
		
		try {
			connection = DAOUtilities.getConnection();
			
			String sql = "{CALL UPDATE_ACC_BAL (?,?)}";
			cs = connection.prepareCall(sql);
			cs.setDouble(1, newtotal);
			cs.setInt(2, u.getAccountID());
			
			cs.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (cs != null)
					cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void withdrawMoney(User u, double d) throws SQLException{
		Connection connection = null;
		CallableStatement cs = null;
		
		double newtotal = u.getAccountBal()-d;
		
		try {
			connection = DAOUtilities.getConnection();
			
			String sql = "{CALL UPDATE_ACC_BAL (?,?)}";
			cs= connection.prepareCall(sql);
			cs.setDouble(1, newtotal);
			cs.setInt(2, u.getAccountID());
			
			cs.execute();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (cs != null)
					cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
