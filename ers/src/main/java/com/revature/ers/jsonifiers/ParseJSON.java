package com.revature.ers.jsonifiers;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseJSON {
	public static JSONObject parsePost(HttpServletRequest request) {
		  StringBuffer jb = new StringBuffer();
		  String line = null;
		  
		  //get the form data
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  //JSONify the data
		  JSONParser parser = new JSONParser(); 
		  try {
			JSONObject json = (JSONObject) parser.parse(jb.toString());
			  return json;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		return null;

	}
}
