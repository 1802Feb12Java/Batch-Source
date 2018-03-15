package com.revature.expensereimbursement.controller.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.expensereimbursement.dao.ERSReimbursementDAO;
import com.revature.expensereimbursement.dao.ERSReimbursementTypeDAO;
import com.revature.expensereimbursement.model.ERSReimbursement;
import com.revature.expensereimbursement.model.ERSReimbursementType;

/**
 * Servlet implementation class Employee
 */
public class Employee extends Index {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Employee do get");		
    	JSONObject json = new JSONObject();
		if(this.getSessionUser(request, response, json, "Employee")) {
			if(request.getParameter("get").equals("reimbursements")) {
	    		this.getReimbursements(json);
	    		//this.getAllReimbursementTypes(json);
	    	}
		}
	    System.out.println(json);
        response.setContentType("application/json");
        response.getWriter().write(json.toString()); 
        return;
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
        response.setContentType("application/json");
		System.out.println("Emplyee doPost");
		if(request.getParameter("create")!=null 
				&& request.getParameter("create").equals("reimbursement") 
				&& this.getSessionUser(request, response, json, "Employee")) {
			
			JSONObject jsonData = this.getRequestBody(request, response, json);
			if(jsonData!=null) {
				this.createReimbursement(jsonData, json);
			    System.out.println(json);
		        response.getWriter().write(json.toString()); 
		        return;
			}
			response.getWriter().write(json.toString()); 
			return;
		}
    	json.put("problem", "unrecognized post request");
    	System.out.println(json);
        response.getWriter().write(json.toString()); 
		return;
	}
	
	
	@SuppressWarnings("unchecked")
	private void getReimbursements(JSONObject json) {
		List<ERSReimbursement> reimbursements = new ArrayList<ERSReimbursement>();
		try {
			reimbursements = new ERSReimbursementDAO().getReimbursementsByAuthor((Integer)json.get("userId"));
		} catch (SQLException e) {
			e.printStackTrace();
			json.put("problem", "Error: unable to retrieve employee reimbursements ");
			return;
		}
		json.put("count", reimbursements.size());
		JSONArray array = new JSONArray();
		for(ERSReimbursement reimbursement: reimbursements) {
			JSONObject temp = new JSONObject();
			Gson gsonbuilder =new GsonBuilder().create();
			String bean = gsonbuilder.toJson(reimbursement);
			JSONParser parser = new JSONParser();
			try {
				temp =(JSONObject) parser.parse(bean);
				array.add(temp);
			} catch (ParseException e) {
				e.printStackTrace();
				json.put("problem", "Error: unable to parse class to JSON");
			}
		}
		json.put("reimbursements", array);
	}
	@SuppressWarnings("unchecked")
	protected void getEmployeeReimbursementTypes(JSONObject json) {
		List<ERSReimbursementType> types = new ArrayList<ERSReimbursementType>();
		try {
			types = new ERSReimbursementTypeDAO().getAllTypes();
		} catch (SQLException e) {
			e.printStackTrace();
			json.put("problem", "Error: unable to retrieve all reimbursements types");
			return;
		}
		json.put("count", types.size());
		JSONArray array = new JSONArray();
		for(ERSReimbursementType type: types) {
			JSONObject temp = new JSONObject();
			Gson gsonbuilder =new GsonBuilder().create();
			String bean = gsonbuilder.toJson(type);
			JSONParser parser = new JSONParser();
			try {
				temp =(JSONObject) parser.parse(bean);
				array.add(temp);
			} catch (ParseException e) {
				e.printStackTrace();
				json.put("problem", "Error: unaable to parse class to JSON");
			}
		}
		json.put("types", array);
	}
}
