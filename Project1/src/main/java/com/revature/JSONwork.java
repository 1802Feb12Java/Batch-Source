package com.revature;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONwork {

	public static String makeArrR(ArrayList<Reimbursement> ar) {
		JSONArray jArr = new JSONArray();
		JSONObject job = new JSONObject();
		
		for(int i = 0; i < ar.size(); i++) {
			job.append("R_ID", ar.get(i).getId());
			job.append("amount", ar.get(i).getAmount());
			job.append("description", ar.get(i).getDescription());
			job.append("author", ar.get(i).getAuthor());
			job.append("resolver", ar.get(i).getResolver());
			job.append("time_submitted", ar.get(i).getTimeSubmitted());
			job.append("time_resolved", ar.get(i).getTimeResolved());
			job.append("", getStatus(ar.get(i).getStatus()));
			job.append("type", getType(ar.get(i).getType()));
			job.append("receipt", ar.get(i).getReceipt());
			//add the json obj to the array to send out
			jArr.put(i, job);
		}
		
		return jArr.toString();
	}
	
	public static String makeArrU(ArrayList<Users> u) {
		JSONArray jArr = new JSONArray();
		JSONObject job = new JSONObject();
		
		for(int i = 0; i < u.size(); i++) {
			job.append("firstname", u.get(i).getfName());
			job.append("lastname", u.get(i).getlName());
			job.append("U_ID", u.get(i).getId());
			job.append("email", u.get(i).getEmail());
			job.append("username", u.get(i).getUsername());
			job.append("password", "**********");
			job.append("role", getRole(u.get(i).getRoleID()));
			//add the json obj to the array to send out
			jArr.put(i, job);
		}
		
		return jArr.toString();
	}
	
	public static String getStatus(int i) {
		String status = "";
		if(i == 1) {
			return "pending";
		} else if(i == 2){
			return "approved";
		} else if(i == 3) {
			return "denied";
		}
		//if there is no status code, there is no reimbursement so it's all empty
		return status;
	}
	
	public static String getType(int i) {
		String type = "";
		if(i == 1) {
			return "Business";
		} else if(i == 2){
			return "Travel";
		} else if(i == 3) {
			return "Medical";
		}
		//if there is no type code, there is no reimbursement so it's all empty
		return type;
	}
	
	public static String getRole(int i) {
		if(i == 1) {
			return "Manager";
		}else {
			return "Employee";
		}
	}
	
}
