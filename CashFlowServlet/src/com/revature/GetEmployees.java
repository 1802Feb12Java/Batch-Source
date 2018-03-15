package com.revature;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.ManagerHistory.Bean;

public class GetEmployees extends Operation {

	String handle(JsonObject request, Connection connection) 
	{
		ArrayList<Bean> results=new ArrayList();
		
		try
        {
			PreparedStatement preparedStatement=null;
	   		ResultSet rs=null;
            String selectSQL = "SELECT U_ID,U_UserName,U_FirstName,U_LastName,U_Email,UR_Role,U_Password\n" + 
            		"FROM Users\n" + 
            		"Inner JOIN User_Roles\n" + 
            		"ON Users.UR_ID = User_Roles.UR_ID";
            preparedStatement = connection.prepareStatement(selectSQL);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                Bean bean=new Bean();
                bean.U_ID=rs.getInt("U_ID");
                bean.U_UserName=rs.getString("U_Username");
                bean.U_FirstName=rs.getString("U_FirstName");
                bean.U_LastName=rs.getString("U_Lastname");
                bean.U_Email=rs.getString("U_Email");
                bean.UR_Role=rs.getString("UR_Role");
                bean.U_Password=rs.getString("U_Password");
                results.add(bean);
            }
        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred at line 44 of GetEmployees.java");
        }
		
		String json=new Gson().toJson(results);
		return json;
	}
	
	class Bean
	{
		int U_ID;
		String U_UserName;
		String U_FirstName;
		String U_LastName;
		String U_Email;
		String UR_Role;
		String U_Password;
	}

}
