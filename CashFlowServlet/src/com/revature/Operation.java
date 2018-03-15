package com.revature;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.google.gson.JsonObject;

abstract class Operation
{
    int parameter;
    abstract String handle(JsonObject request,Connection connection);

    static Connection inherited_connection = null;
    
	public int getTypeID(String type) {
		int tid=0;
		
	       try
	        {
	    	    		PreparedStatement preparedStatement=null;
	    	    		ResultSet rs=null;
	            String selectSQL = "SELECT RT_ID FROM Reimbursement_Type where RT_Type=?";
	            preparedStatement = inherited_connection.prepareStatement(selectSQL);
	            preparedStatement.setString(1, type);
	            preparedStatement.executeQuery();
	            rs = preparedStatement.executeQuery();

	            if(rs.next())
	                tid = rs.getInt(1);
	      
	        }
	        catch (SQLException e)
	        {
	            System.out.println("An SQL exception occurred 98");
	        }
	       
		return tid;
	}

	public int getUID(String username) 
	{
		
		int uid=0;
		
	       try
	        {
	    	   		PreparedStatement preparedStatement=null;
	    	   		ResultSet rs=null;
	            String selectSQL = "SELECT U_ID FROM Users where U_Username=?";
	            preparedStatement = inherited_connection.prepareStatement(selectSQL);
	            preparedStatement.setString(1, username);
	            preparedStatement.executeQuery();
	            rs = preparedStatement.executeQuery();

	            if(rs.next())
	                uid = rs.getInt(1);
	      
	        }
	        catch (SQLException e)
	        {
	            System.out.println("An SQL exception occurred 99");
	        }
	       
		return uid;
	}
	
	public String getUsername(int uid) 
	{
		
		String username="";
		
	       try
	        {
	    	   		PreparedStatement preparedStatement=null;
	    	   		ResultSet rs=null;
	            String selectSQL = "SELECT U_UserName FROM Users where U_ID=?";
	            preparedStatement = inherited_connection.prepareStatement(selectSQL);
	            preparedStatement.setInt(1, uid);
	            preparedStatement.executeQuery();
	            rs = preparedStatement.executeQuery();

	            if(rs.next())
	                username = rs.getString(1);
	      
	        }
	        catch (SQLException e)
	        {
	            System.out.println("An SQL exception occurred 77");
	        }
	       
		return username;
	}
	
	public String getType(int type) {
		String Type="";
		
	       try
	        {
	    	   		PreparedStatement preparedStatement=null;
	    	   		ResultSet rs=null;
	            String selectSQL = "SELECT RT_TYPE FROM Reimbursement_Type where RT_ID=?";
	            preparedStatement = inherited_connection.prepareStatement(selectSQL);
	            preparedStatement.setInt(1, type);
	            preparedStatement.executeQuery();
	            rs = preparedStatement.executeQuery();

	            if(rs.next())
	                Type = rs.getString(1);
	      
	        }
	        catch (SQLException e)
	        {
	            System.out.println("An SQL exception occurred 88");
	        }
	       
		return Type;
	}
	
	public String getStatus(int type) {
		String Status="";
		
	       try
	        {
	    	   		PreparedStatement preparedStatement=null;
	    	   		ResultSet rs=null;
	            String selectSQL = "SELECT RS_STATUS FROM Reimbursement_Status where RS_ID=?";
	            preparedStatement = inherited_connection.prepareStatement(selectSQL);
	            preparedStatement.setInt(1, type);
	            preparedStatement.executeQuery();
	            rs = preparedStatement.executeQuery();

	            if(rs.next())
	                Status = rs.getString(1);
	      
	        }
	        catch (SQLException e)
	        {
	            System.out.println("An SQL exception occurred 66");
	        }
	       
		return Status;
	}
	
	public String getFirst(int type) {
		String First="";
		
	       try
	        {
	    	   		PreparedStatement preparedStatement=null;
	    	   		ResultSet rs=null;
	            String selectSQL = "SELECT U_FIRSTNAME FROM Users where U_ID=?";
	            preparedStatement = inherited_connection.prepareStatement(selectSQL);
	            preparedStatement.setInt(1, type);
	            preparedStatement.executeQuery();
	            rs = preparedStatement.executeQuery();

	            if(rs.next())
	                First = rs.getString(1);
	      
	        }
	        catch (SQLException e)
	        {
	            System.out.println("An SQL exception occurred 94");
	        }
	       
		return First;
	}
	
	public String getLast(int type) {
		String Last="";
		
	       try
	        {
	    	   		PreparedStatement preparedStatement=null;
	    	   		ResultSet rs=null;
	            String selectSQL = "SELECT U_LASTNAME FROM Users where U_ID=?";
	            preparedStatement = inherited_connection.prepareStatement(selectSQL);
	            preparedStatement.setInt(1, type);
	            preparedStatement.executeQuery();
	            rs = preparedStatement.executeQuery();

	            if(rs.next())
	                Last = rs.getString(1);
	      
	        }
	        catch (SQLException e)
	        {
	            System.out.println("An SQL exception occurred 94");
	        }
	       
		return Last;
	}


    


}
