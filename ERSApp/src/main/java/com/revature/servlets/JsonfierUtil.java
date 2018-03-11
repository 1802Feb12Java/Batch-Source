package com.revature.servlets;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.beans.Reimbursement;

public class JsonfierUtil {

	public static String reimListJson(ArrayList<Reimbursement> list) {
		JSONArray json = new JSONArray();

		JSONObject obj = null;
		// put in all displayable/useful fields
		for (Reimbursement r : list) {
			obj = new JSONObject();
			obj.put("amount", r.getAmount());
			obj.put("description", r.getDecription());
			obj.put("receipt", r.getRecipt());
			obj.put("submitted", r.getSubmitted());
			obj.put("resolved", r.getResolved());
			obj.put("resolver", r.getResolverId());
			obj.put("type", r.getType());
			obj.put("status", r.getStatus());

			// put reim item in array
			json.put(obj);
		}
		return json.toString();
	}
}
