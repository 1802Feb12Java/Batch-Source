package com.revature.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;

public class JsonfierUtil {
	private static SimpleDateFormat timeStampFormat = new SimpleDateFormat("hh:mm a MM/dd/yyyy");

	public static String reimListJson(ArrayList<Reimbursement> list) {
		JSONArray json = new JSONArray();

		JSONObject obj = null;
		// put in all displayable/useful fields
		for (Reimbursement r : list) {
			obj = new JSONObject();
			obj.put("amount", r.getAmount());
			obj.put("description", r.getDecription());
			obj.put("receipt", r.getRecipt());
			// format times before putting them in
			obj.put("submitted", timeStampFormat.format(r.getSubmitted()));
			if (r.getResolved() != null)
				obj.put("resolved", timeStampFormat.format(r.getResolved()));
			else
				obj.put("resolved", "null");
			obj.put("resolver", r.getResolverName());
			obj.put("type", r.getType());
			obj.put("status", r.getStatus());
			// PICTURE PART
			obj.put("receipt", r.getReimbursementId());
			// need id
			obj.put("reimId", r.getReimbursementId());

			// put reim item in array
			json.put(obj);
		}
		return json.toString();
	}

	public static String userListJson(ArrayList<User> list) {
		JSONArray json = new JSONArray();

		JSONObject obj = null;
		// put in all displayable/useful fields
		for (User u : list) {
			obj = new JSONObject();
			obj.put("username", u.getUsername());
			obj.put("firstname", u.getFirstName());
			obj.put("lastname", u.getLastName());
			obj.put("email", u.getEmail());

			// put user item in array
			json.put(obj);
		}
		return json.toString();
	}

	/**
	 * Gets firstname lastname from user obj and jsons it
	 * 
	 * @param u
	 * @return
	 */
	public static String userJson(User u) {
		JSONObject obj = new JSONObject();
		obj.put("firstname", u.getFirstName());
		obj.put("lastname", u.getLastName());

		return obj.toString();
	}
}
