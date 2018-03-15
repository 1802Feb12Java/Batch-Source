package com.revature;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.EmpHistory.Bean;

public class EmpInfoGet extends Operation {

	@Override
	String handle(JsonObject request, Connection connection) 
	{
		String username=request.get("username").getAsString();
        Bean bean=new Bean();
        
		try
        {
			PreparedStatement preparedStatement=null;
	   		ResultSet rs=null;
            String selectSQL = "SELECT U_USERNAME,U_FIRSTNAME,U_LASTNAME,U_EMAIL,U_PASSWORD FROM USERS WHERE U_ID=?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, getUID(username));
            rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                bean.username=rs.getString("U_USERNAME");
                bean.firstname=rs.getString("U_FIRSTNAME");
                bean.lastname=rs.getString("U_LASTNAME");
                bean.email=rs.getString("U_EMAIL");
                bean.password=rs.getString("U_PASSWORD");
            }
        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred at line 41 in EmpInfoGet.java");
        }
		
		String json=new Gson().toJson(bean);
		return json;
	}
	
	class Bean
	{
		String username;
		String firstname;
		String lastname;
		String email;
		String password;
	}

}
