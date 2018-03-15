package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.EmpHistory.Bean;

public class EmpInfo extends Operation {

	@Override
	String handle(JsonObject request, Connection connection) 
	{

		String username=request.get("username").getAsString();
		String new_username=request.get("new_username").getAsString();
		String new_first=request.get("new_first").getAsString();
		String new_last=request.get("new_last").getAsString();
		String new_email=request.get("new_email").getAsString();
		String new_password=request.get("new_password").getAsString();
		
		try
        {
			PreparedStatement preparedStatement=null;
	   		ResultSet rs=null;
            String selectSQL = "UPDATE USERS SET U_USERNAME=?, U_FIRSTNAME=?,U_LASTNAME=?,U_EMAIL=?,U_PASSWORD=? WHERE U_ID=?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, new_username);
            preparedStatement.setString(2, new_first);
            preparedStatement.setString(3, new_last);
            preparedStatement.setString(4, new_email);
            preparedStatement.setString(5, new_password);
            preparedStatement.setInt(6, getUID(username));
            rs = preparedStatement.executeQuery();
        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 895");
        }
		
		return "updated";
	}

}
