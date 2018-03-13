package com.revature.ers.jsonifiers;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.ers.users.User;

public class ToJSON {
	public static JSONObject employee(User user) {
		JSONObject emp = new JSONObject();

		//convert the user data to JSON
		emp.put("username", user.getU_userName());
		emp.put("fname", user.getU_firstName());
		emp.put("lname", user.getU_lastName());
		emp.put("email", user.getU_email());
		
		if(user.getUr_ID()==1) {
			emp.put("role", "Manager");
		}
		
		else {
			emp.put("role", "Employee");
		}

		return emp;
	}
	
	public static JSONArray employeeList() {
		JSONObject emp = new JSONObject();
		JSONArray empList = new JSONArray();
		
		return empList;
	}
	
	public static JSONArray empPending(int u_ID) {
		JSONObject reimbursement = new JSONObject();
		JSONArray reimPending = new JSONArray();
		
		return reimPending;
	}
	
	public static JSONArray empResolved(int u_ID) {
		JSONObject reimbursement = new JSONObject();
		JSONArray reimResolved = new JSONArray();	
		
		return reimResolved;
	}
	
	public static JSONArray allPending() {
		JSONObject reimbursement = new JSONObject();
		JSONArray reimPending = new JSONArray();
		
		return reimPending;		
	}
	
	public static JSONArray allResolved() {
		JSONObject reimbursement = new JSONObject();
		JSONArray reimResolved = new JSONArray();
		
		return reimResolved;		
	}	
}
