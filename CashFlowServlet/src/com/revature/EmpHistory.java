package com.revature;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class EmpHistory extends Operation {

	@Override
	String handle(JsonObject request, Connection connection) 
	{
		ArrayList<Bean> results=new ArrayList();
		String username=request.get("username").getAsString();
		
		try
        {
			PreparedStatement preparedStatement=null;
	   		ResultSet rs=null;
            String selectSQL = "SELECT * FROM REIMBURSEMENTS WHERE U_ID_AUTHOR=(Select U_ID FROM USERS WHERE U_USERNAME=?)";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                Bean bean=new Bean();
                bean.R_ID=rs.getInt("R_ID");
                bean.R_AMOUNT=rs.getDouble("R_AMOUNT");
                bean.R_DESCRIPTION=rs.getString("R_DESCRIPTION");
                bean.R_RECEIPT=rs.getString("R_RECEIPT");
                bean.R_SUBMITTED=rs.getString("R_SUBMITTED");
        		 	bean.R_RESOLVED=rs.getString("R_RESOLVED");
        		 	bean.U_ID_AUTHOR=getUsername(rs.getInt("U_ID_AUTHOR"));
        		 	bean.U_ID_RESOLVER=rs.getInt("U_ID_RESOLVER");
        		 	bean.RT_TYPE=getType(rs.getInt("RT_TYPE"));
        		 	bean.RT_STATUS=getStatus(rs.getInt("RT_STATUS"));
        		 	results.add(bean);
            }
        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 59");
        }
		
		String json=new Gson().toJson(results);
		return json;
	}
	
	class Bean
	{
		int R_ID;
		double R_AMOUNT;
		String R_DESCRIPTION;
		String R_RECEIPT;
		String R_SUBMITTED;
		String R_RESOLVED;
		String U_ID_AUTHOR;
		int U_ID_RESOLVER;
		String RT_TYPE;
		String RT_STATUS;
	}
}
