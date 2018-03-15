package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.google.gson.JsonObject;

public class UpdateRStatus extends Operation {

	@Override
	String handle(JsonObject request, Connection connection) 
	{
		int r_id=request.get("r_id").getAsInt();
		String status=request.get("status").getAsString();
		String resolver=request.get("resolver").getAsString();
		try
        {
			PreparedStatement preparedStatement=null;
	   		ResultSet rs=null;
            String selectSQL = "UPDATE Reimbursements SET RT_STATUS=(Select RS_ID FROM REIMBURSEMENT_STATUS WHERE RS_STATUS=?), U_ID_Resolver=?, R_RESOLVED=? WHERE R_ID=?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2,getUID(resolver) );
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(4, r_id);
            rs = preparedStatement.executeQuery();
        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred at line 30 of UpdateRStatus");
        }
		
		return "updated";
	}

}
