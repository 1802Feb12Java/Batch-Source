package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.FlashCard;

import oracle.jdbc.OracleTypes;

public class DaoFlashCard implements DAO{
	
	private final static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final static String username = "flashcard_db";
	private final static String password = "p4ssw0rd";
	
	
	@Override
	public void insertNewFC(int id, String question, String answer) {
		
		try (Connection con = DriverManager.getConnection(url,username,password);) {
			String sqlInsert = "INSERT INTO flash_cards(fc_question, fc_answer) VALUES(?,?)";
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setString(1, question);
			ps.setString(2, answer);
			int numRowsAffected = ps.executeUpdate();
			System.out.println("# of rows changed: " + numRowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void updateAFC(FlashCard fc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrieveFCById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFCById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FlashCard> retrieveAllFc() {
		try (Connection con = DriverManager.getConnection(url,username,password);) {
			
			List<FlashCard> fcList = new ArrayList<>();
			
			String sql = "select * FROM flash_cards";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				FlashCard thisFc = new FlashCard(rs.getInt(1),rs.getString(2),rs.getString(3));
				fcList.add(thisFc);
			}
			return fcList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void createFlashCardPreparedStatement(FlashCard flashCard) {
		//Preparedstatement
		try (Connection connect = DriverManager.getConnection(url,username,password);){

			//Set autoCommit to false - Starts a Transaction in JDBC
			connect.setAutoCommit(false); 
			
			//Add primaryKey String Array to our prepared statement -OPTIONAL
			String[] primaryKeys = new String[1];
			primaryKeys[0] = "fc_id";
			String sql = "INSERT INTO flash_cards(fc_question,fc_answer) VALUES(?,?)";
			PreparedStatement ps = connect.prepareStatement(sql, primaryKeys); //2nd Argument is OPTIONAL
			ps.setString(1, flashCard.getFcQuestion());
			ps.setString(2, flashCard.getFcAnswer());
			
			//By default execute method will auto commit, must set to false
			//When trying to run a transaction - multiple sql statement
			// where you want all or nothing!
			int numRowsAffected = ps.executeUpdate(); 
			
			System.out.println("Num of Rows affected - PreparedStatement: " + numRowsAffected);
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			
			System.out.println("Generated Keys: ");
			while(generatedKeys.next()){
				System.out.println(generatedKeys.getInt(1)); //column number
			}
			
			//With auto commit set to false
			connect.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<FlashCard> retrieveAllFlashCards() {
		/**
		 * This will be using a procedure that has a CURSOR OUT param.
		 * You will find this procedure in the Script pushed to GitLab
		 */
		
		List<FlashCard> fcList = new ArrayList<>();
			try (Connection connect = DriverManager.getConnection(url,username,password);) {
				
				String getFCbyStoredProc = "{call get_all_fc_procedure(?)}";
				
				CallableStatement cs = connect.prepareCall(getFCbyStoredProc);
				cs.registerOutParameter(1, OracleTypes.CURSOR);
				
				int numRow = cs.executeUpdate();
				System.out.println(numRow + " effected");
				
				ResultSet rs = (ResultSet) cs.getObject(1);
				while(rs.next()){
					fcList.add(new FlashCard(rs.getInt(1), rs.getString(2), rs.getString(3)));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return fcList;
	}
}
