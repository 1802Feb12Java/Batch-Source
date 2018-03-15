package com.revature.expensereimbursement.controller.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.runner.Request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.expensereimbursement.dao.ERSReimbursementDAO;
import com.revature.expensereimbursement.dao.ERSReimbursementStatusDAO;
import com.revature.expensereimbursement.dao.ERSUserDAO;
import com.revature.expensereimbursement.model.ERSReimbursement;
import com.revature.expensereimbursement.model.ERSReimbursementStatus;
import com.revature.expensereimbursement.model.ERSUser;

/**
 * Servlet implementation class Manager
 */
public class Manager extends Index {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Manager do get");
    	JSONObject json = new JSONObject();
		if(this.getSessionUser(request, response, json, "Manager")) {
			if(request.getParameter("get").equals("reimbursements")) {
	    		this.getAllReimbursements(json);
	    		this.getAllReimbursementStatuses(json);
	    	}else if (request.getParameter("get").equals("employees")) {
	    		this.getAllEmployees(json);
	    		this.getAllEmployeeRoles(json);
	    	}
		}
	    //System.out.println(json);
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
		System.out.println("Manager doPost");
		if(request.getParameter("create")!=null && this.getSessionUser(request, response, json, "Manager")){
			if( request.getParameter("create").equals("employee")) {
				JSONObject jsonData = this.getRequestBody(request, response, json);
				if(jsonData!=null) {
					this.createEmployee(jsonData, json);
				    System.out.println(json);
				}else {
					json.put("problem", "no content received");
				}
				response.getWriter().write(json.toString()); 
				return;
			}else if(request.getParameter("create").equals("reimbursement")) {
				JSONObject jsonData = this.getRequestBody(request, response, json);
				if(jsonData!=null) {
					this.createReimbursement(jsonData, json);
				    System.out.println(json);
				}else {
					json.put("problem", "no content received");
				}
				response.getWriter().write(json.toString()); 
				return;
			}
	    	json.put("problem", "unrecognized post request");
	    	System.out.println(json);
	        response.getWriter().write(json.toString()); 
			return;
		}
	}
	@SuppressWarnings("unchecked")
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
        response.setContentType("application/json");
		System.out.println("Manager doPut");
		if(request.getParameter("update")!=null 
				&& request.getParameter("update").equals("reimbursement") 
				&& this.getSessionUser(request, response, json, "Manager")) {
			JSONObject jsonData = this.getRequestBody(request, response, json);
			if(jsonData!=null && jsonData.get("id")!=null && jsonData.get("statusId")!=null) {
				this.updateReimbursement(jsonData, json);
			    System.out.println(json);
		        response.getWriter().write(json.toString()); 
		        return;
			}
			json.put("update", "failed");
			response.getWriter().write(json.toString()); 
			return;
		}
    	json.put("problem", "unrecognized put request");
        response.getWriter().write(json.toString()); 
		return;
	}
	@SuppressWarnings("unchecked")
	protected void updateReimbursement(JSONObject reimbursement, JSONObject json) {
		System.out.println(((Long) reimbursement.get("id")));
		System.out.println(((Long) reimbursement.get("id")).intValue());
		System.out.println(((Long) reimbursement.get("statusId")));
		System.out.println(((Long) reimbursement.get("statusId")).intValue());
		ERSReimbursement update= new ERSReimbursement(((Long) reimbursement.get("id")).intValue(), 
				(Integer) json.get("userId"), ((Long) reimbursement.get("statusId")).intValue());
		try {
			if(new ERSReimbursementDAO().updateReimbursement(update)) {
				json.put("update", "updated");
			}else {
				json.put("update", "failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			json.put("update", "failed");
		}	
		System.out.println("update reimbursement " + update.toString());
	}

	@SuppressWarnings("unchecked")
	private void getAllReimbursements(JSONObject json) {
		System.out.println("reimbursements");
		List<ERSReimbursement> reimbursements = null;
		try {
			reimbursements = new ERSReimbursementDAO().getAllReimbursements();
		} catch (SQLException e) {
			e.printStackTrace();
			json.put("problem", "Error: unable to retrieve all reimbursements reimbursements");
			return;
		}
		JSONArray array = new JSONArray();
		if(reimbursements==null) {
			json.put("reimbursements", array);
			return;
		}
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
		System.out.println("reimbursements");
		System.out.println(reimbursements);
	}
	@SuppressWarnings("unchecked")
	private void getAllReimbursementStatuses(JSONObject json) {
		List<ERSReimbursementStatus> statuses = null;
		try {
			statuses = new ERSReimbursementStatusDAO().getAllStatuses();
		} catch (SQLException e) {
			e.printStackTrace();
			json.put("problem", "Error: unable to retrieve all reimbursements statuses");
			return;
		}
		JSONArray array = new JSONArray();
		if(statuses==null) {
			json.put("statuses", array);
			return;
		}
		json.put("count", statuses.size());
		for(ERSReimbursementStatus status: statuses) {
			JSONObject temp = new JSONObject();
			Gson gsonbuilder =new GsonBuilder().create();
			String bean = gsonbuilder.toJson(status);
			JSONParser parser = new JSONParser();
			try {
				temp =(JSONObject) parser.parse(bean);
				array.add(temp);
			} catch (ParseException e) {
				e.printStackTrace();
				json.put("problem", "Error: unaable to parse class to JSON");
			}
		}
		json.put("statuses", array);
	}
	
	@SuppressWarnings("unchecked")
	private void getAllEmployees(JSONObject json) {
		List<ERSUser> users = null;
		try {
			users = new ERSUserDAO().getAllUsers();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			json.put("problem", "Error: unable to retrieve all users");
			return;
		}
		JSONArray array = new JSONArray();
		if(users==null) {
			json.put("problem", "Error: no user id found");
			return;
	    }
		json.put("employees", array);
		for(ERSUser user: users) {
			JSONObject temp = new JSONObject();
			Gson gsonbuilder =new GsonBuilder().create();
			String bean = gsonbuilder.toJson(user);
			//System.out.println(bean);
			JSONParser parser = new JSONParser();
			try {
				temp =(JSONObject) parser.parse(bean);
				//System.out.println(temp);
				array.add(temp);
			} catch (ParseException e) {
				e.printStackTrace();
				json.put("problem", "Error: unaable to parse class to JSON");
			}
		}
		json.put("employees", array);
	}
	@SuppressWarnings("unchecked")
	private void createEmployee(JSONObject jsonData, JSONObject json) {
		try {
			if(new ERSUserDAO().getUserByUsername((String) jsonData.get("username"))!=null) {
				json.put("problem", "Select another username");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ERSUser user = new ERSUser( (String) jsonData.get("username"), (String) jsonData.get("password"),
				(String) jsonData.get("firstName"), (String) jsonData.get("lastName"), (String) jsonData.get("email"),
				Integer.parseInt((String) jsonData.get("roleId")), (String) jsonData.get("role")); 
		try {
			if( new ERSUserDAO().addUser(user)) {
				json.put("create", "success");
			}else {
				json.put("create", "fail");
			}
		} catch (SQLException e) {
			json.put("create", "fail");
			e.printStackTrace();
		}
	}
	
}
