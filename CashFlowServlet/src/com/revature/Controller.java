package com.revature;

import java.sql.Connection;

import com.google.gson.JsonObject;

public class Controller
{
    public String handle(JsonObject request, int parameter, Connection connection)
    {
        Operation operation=control_board(parameter);
        return operation.handle(request,connection);
    }

    private Operation control_board(int parameter)
    {
    		if(parameter==2)
    			return new Register_Employee();
    		else if(parameter==3)
        		return new RForm_Submit();
        else if(parameter==4)
        		return new EmpHistory();
        else if(parameter==5)
        		return new EmpInfo();
        else if(parameter==10)
        		return new EmpInfoGet();
        else if(parameter==6)
        		return new ManagerHistory();
        else if(parameter==7)
        		return new GetEmployees();
        else if(parameter==11)
        		return new UpdateRStatus();
        else if(parameter==12)
        		return new LabelCreatorforAWS();
        System.out.println("Control board did not catch parameter :"+parameter);
        return null;
    }
}
