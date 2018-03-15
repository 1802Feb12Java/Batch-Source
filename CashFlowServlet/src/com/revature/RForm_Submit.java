package com.revature;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.google.gson.JsonObject;

public class RForm_Submit extends Operation {

	@Override
	String handle(JsonObject request, Connection connection) 
	{
		System.out.println(request.get("amount").getAsString()+"HI"+request.get("description").getAsString()+"HI"+request.get("username").getAsString()+"HI"+request.get("type").getAsString()+"HI");
		try
        {
			//Edit to include blob
            CallableStatement submit = connection.prepareCall("{call insert_reimbursement(?,?,?,?,?,?)}");
            submit.setDouble(1, request.get("amount").getAsDouble());
            submit.setString(2, request.get("description").getAsString());
            submit.setInt(3, getUID(request.get("username").getAsString()));
            submit.setInt(4,getTypeID(request.get("type").getAsString()));
            submit.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            submit.setString(6, request.get("receipt").getAsString());
            
            
            
            //CHANGE THE FUNCTION
            
            //rewrite sql to use nested select statements
            submit.execute();

        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 503");
        }
		
		return "table updated";
	}
}
