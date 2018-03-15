package com.revature.ers.jsonifiers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.ers.reimbursements.Reimbursement;
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
	
	public static JSONArray reimbursements(ArrayList<Reimbursement> list) {
		if (list.isEmpty()) {
			return null;
		}

		JSONArray jArray = new JSONArray();
		
		for (Reimbursement indexed : list) {
			JSONObject current = new JSONObject();
			current.put("r_id", indexed.getR_id());
			current.put("amount", indexed.getR_amount());
			current.put("description", indexed.getR_description());
			current.put("receipt", indexed.getR_receipt());
			current.put("submitted", indexed.getR_submitted());
			current.put("resolved", indexed.getR_resolved());
			current.put("author", indexed.getU_ID_Author());
			current.put("resolver", indexed.getU_ID_Resolver());
			current.put("type", indexed.getRt_Type());
			current.put("status",  indexed.getRt_Status());
			
			jArray.put(current);
		}
		
		return jArray;
	}	
}
