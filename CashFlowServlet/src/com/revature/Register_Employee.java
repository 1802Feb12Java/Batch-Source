package com.revature;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Register_Employee extends Operation {


	@Override
	String handle(JsonObject request, Connection connection) {
		Bean bean=new Bean();
		try
        {
			
			//Edit to include blob
            CallableStatement submit = connection.prepareCall("{call insert_user(?,?,?,?,?,?)}");
            submit.setString(1, request.get("username").getAsString());
            submit.setString(2, request.get("password").getAsString());
            submit.setString(3, request.get("fn").getAsString());
            submit.setString(4, request.get("ln").getAsString());
            submit.setString(5, request.get("email").getAsString());
            submit.setInt(6, 2);

            //CHANGE THE FUNCTION
            
            //rewrite sql to use nested select statements
            submit.execute();

        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 503");
            bean.status_message= "taken";
            String json=new Gson().toJson(bean);
    			return json;
        }
		
		bean.status_message= "table updated";
		String json=new Gson().toJson(bean);
		return json;
	}

}

class Bean
{
	String status_message;
}
