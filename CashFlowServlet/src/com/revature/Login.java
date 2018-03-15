package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;

public class Login extends Operation
{

    @Override
    String handle(JsonObject request,Connection connection)
    {
    		inherited_connection=connection;
        Bean bean=new Bean();
        String username=request.get("username").getAsString();
        String password=request.get("password").getAsString();

        if(verify(username,password))
        {
            bean.verified=1;
            bean.role=getRole(username);
        }

        String json=new Gson().toJson(bean);
        System.out.println("JSON leaving servlet:"+json);
        return json;
    }

    private String getRole(String username) 
    {
    	
    			String role="";
    		
    	       try
    	        {
    	    	   		PreparedStatement preparedStatement=null;
	    	   		ResultSet rs=null;
    	            String selectSQL = "SELECT UR_ROLE FROM Users Inner JOIN User_Roles ON Users.UR_ID = User_Roles.UR_ID where Users.U_Username=?";
    	            preparedStatement = inherited_connection.prepareStatement(selectSQL);
    	            preparedStatement.setString(1, username);
    	            preparedStatement.executeQuery();
    	            rs = preparedStatement.executeQuery();

    	            if(rs.next())
    	                role = rs.getString(1);
    	      
    	        }
    	        catch (SQLException e)
    	        {
    	            System.out.println("An SQL exception occurred 403");
    	        }
    	       
    	       return role;
	}

	private boolean verify(String username, String password) 
    {
    		boolean found=false;
    		try
        {
    			PreparedStatement preparedStatement=null;
    	   		ResultSet rs=null;
            String selectSQL = "SELECT COUNT(1) FROM USERS WHERE U_USERNAME=? AND U_PASSWORD=?";
            preparedStatement = inherited_connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                int count = rs.getInt(1);
                if (count > 0)
                    found=true;
            }

        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred at line 51 of Login.java");
        }

		return found;
	}

	class Bean
    {
        int verified=0;
        String role;
    }
}
