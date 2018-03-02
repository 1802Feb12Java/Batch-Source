package com.revature.bankingappnew.daoimplementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.revature.bankingappnew.ConnectionHandling;

public class TransactionLogImp {
	
	private static Logger log = Logger.getLogger(TransactionLogImp.class.getName());
	
	public static void createTransactionRecord(int accountID, String transactionType, double amount, int userID) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "INSERT INTO transactionLog (transactionID, accountID, transactionType, amountOfTransaction, dateOfTransaction, performedBy)"
				+ " VALUES (getNextTransID(), ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, accountID);
		statement.setString(2, transactionType);
		statement.setDouble(3, amount);
		statement.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
		statement.setInt(5, userID);
		
		
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println();
			System.out.println("**************************DB Message:**************************");
			System.out.println("This transaction has been recorded into the transaction log!");
			System.out.println("***************************************************************");
			System.out.println();
			log.info("Data Base Update: A new record has been added to the transactionLog table.");
			log.debug("Debug Message: A new record was added to the transactionLog table.");
		}
		
	}//end createTransactionRecord method
	
	public static void viewAccountTransactions(int accountNum) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "SELECT * FROM transactionLog WHERE accountID = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, accountNum);
		System.out.println("		Transaction Log for Account Number: " + accountNum);
		System.out.println("___________________________________________________________________________________");
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			String transType = result.getString(3);
			double amount = result.getDouble(4);
			java.sql.Date date = result.getDate(5);
			int userID = result.getInt(6);
			String performedBy = UserLogImp.getUserObject(UserLogImp.getUserName(userID)).getFirstName() + UserLogImp.getUserObject(UserLogImp.getUserName(userID)).getLastName();
			
			System.out.println("Date of Transaction: " + date.toString() + "		" + "Performed By: " + performedBy );
			System.out.println("Transaction Type: " + transType + "		" + "Amount of Transaction: $" + amount);
			System.out.println("------------------------------------------------------------------------");
		}//end while
		System.out.println();
		log.info("Transaction Log was accessed.");
	}//end viewAccountTransactions method
	
	public static void viewUserTransactions(String username) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql;
		PreparedStatement statement;
		if(username.equalsIgnoreCase("all")) {
			sql = "SELECT * FROM transactionLog";
			statement = conn.prepareStatement(sql);
		} else {
			sql = "SELECT * FROM transactionLog WHERE performedBy = ?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, UserLogImp.getUserID(username));
		}
		System.out.println("				Transaction Log");
		System.out.println("_____________________________________________________________________________________");
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			int accountNum = result.getInt(2);
			String transType = result.getString(3);
			double amount = result.getDouble(4);
			java.sql.Date date = result.getDate(5);
			int userID = result.getInt(6);
			String performedBy = UserLogImp.getUserObject(UserLogImp.getUserName(userID)).getFirstName() + UserLogImp.getUserObject(UserLogImp.getUserName(userID)).getLastName();
			System.out.println("			Account Number: " + accountNum);
			System.out.println("Date of Transaction: " + date.toString() + "		" + "Performed By: " + performedBy );
			System.out.println("Transaction Type: " + transType + "		" + "Amount of Transaction: $" + amount);
			System.out.println("------------------------------------------------------------------------");
		}//end while
		System.out.println();
		log.info("Transaction Log was accessed.");
	}//end viewUserTransactions method
	
}//end class
