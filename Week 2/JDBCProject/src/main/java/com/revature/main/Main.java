package com.revature.main;

import java.util.List;

import com.revature.dao.DAO;
import com.revature.dao.DaoFlashCard;
import com.revature.pojo.FlashCard;

public class Main {

	public static void main(String[] args) {
		
		DAO dao = new DaoFlashCard();
		dao.insertNewFC(1, "Hello from?", "The DAO!");
		List<FlashCard> businessList = dao.retrieveAllFc();
		
		for(FlashCard fc : businessList){
			System.out.println(fc);
		}
		
		
//		FlashCard myFC = new FlashCard(1, "Java?", "Yes");
//		
//		System.out.println(myFC);
		
		/**
		 * What are the things we need for Connection to a DB?
		 * 4 things we need
		 * 1. URL
		 * 2. Username
		 * 3. Password
		 * 4. Driver - jars for the specific DB eg Oracle OJDBC7.jar
		 */
		
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String username = "flashcard_db";
//		String password = "p4ssw0rd";
//		
//			//Connection Interface First of the 4 Important Interfaces
//		try (Connection con = DriverManager.getConnection(url,username,password);) {
//			
//			String sql = "select * FROM flash_cards";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while(rs.next()){
//				System.out.println( rs.getInt(1) + " "+ rs.getString(2) + " " + rs.getString(3) );
//			}
			
			/*
			 * TCL - Transaction Control Lang.
			 * Rollback
			 * Savepoint
			 * commit
			 */
			
//			con.setAutoCommit(false); //Starts a Transaction in JDBC
//			String sqlInsert = "INSERT INTO flash_cards(fc_question, fc_answer) VALUES('" + q + "', '" + a + "')";
//			String sqlInsert1 = "INSERT INTO flash_cards(fc_question, fc_answer) VALUES('" + q + "', '" + a + "')";
//			String sqlInsert2 = "INSERT INTO flash_cards(fc_question, fc_answer) VALUES('" + q + "', '" + a + "')";
//			statement.executeUpdate(sqlInsert);
//			statement.executeUpdate(sqlInsert1);
//			statement.executeUpdate(sqlInsert2);
//			con.commit();
			
			
			
			
			/**
			 * CallableStatement is the 4th Important Interface
			 * 		Used to call Stored Procs
			 */
//			String sqlStoredProc = "{call insert_fc_proc(?,?)}";
//			CallableStatement cs = con.prepareCall(sqlStoredProc);
//			cs.setString(1, "Callable?");
//			cs.setString(2, "Calls Stored Procedures");
//			cs.executeUpdate();
//			
//			
//			
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
	}

}
