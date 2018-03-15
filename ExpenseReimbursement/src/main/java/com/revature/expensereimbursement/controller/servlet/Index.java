package com.revature.expensereimbursement.controller.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.expensereimbursement.DAOUtilities;
import com.revature.expensereimbursement.dao.ERSReimbursementDAO;
import com.revature.expensereimbursement.dao.ERSReimbursementTypeDAO;
import com.revature.expensereimbursement.dao.ERSUserDAO;
import com.revature.expensereimbursement.dao.ERSUserRoleDAO;
import com.revature.expensereimbursement.model.ERSReimbursement;
import com.revature.expensereimbursement.model.ERSReimbursementType;
import com.revature.expensereimbursement.model.ERSUser;
import com.revature.expensereimbursement.model.ERSUserRole;

/**
 * Servlet implementation class Login
 */
public class Index extends HttpServlet {
	private static final Logger log = Logger.getLogger(Index.class);
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Index doGet");
		request.getRequestDispatcher("index.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		System.out.println("Index doPost");
        response.setContentType("application/json");
	    JSONObject jsonData = getRequestBody(request, response, json);
		System.out.println("jsonData" + jsonData.toJSONString());
		if(jsonData==null || jsonData.isEmpty()) {
	    	System.out.println(json);
			response.getWriter().write(json.toString()); 
			return;
		}
		String username = (String) jsonData.get("username");
		String password = (String) jsonData.get("password");
		System.out.println("username: "  + username + " pass: " + password);
		ERSUser user = null;
		try {
			user = new ERSUserDAO().getUserByUsername(username);
			System.out.println(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String problem =(user==null)? "Incorrect username" : ((!user.getPassword().equals(password)) ? "Incorrect password":"" );
		if(!problem.isEmpty()) {
			json.put("problem", problem);
	    	System.out.println("problem" + json);
	        response.getWriter().write(json.toString()); 
			return;
		}
	    HttpSession session = request.getSession();
		session.setAttribute("userId", user.getUserId());
		json.put("userRole", user.getUserRole());
		json.put("userFirstName", user.getFirstName());
		json.put("userLastName", user.getLastName());
		json.put("email", user.getEmail());
		json.put("username", user.getUsername());
		this.getAllReimbursementTypes(json);
		if (user.getUserRole().equals("Manager"))
			this.getAllEmployeeRoles(json);
        log.info("Logged in " + json.get("username") );
        System.out.println("Logged in " + json);
        response.getWriter().write(json.toString()); 
        
	}
	@SuppressWarnings("unchecked")
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		System.out.println("Index doPut");
        response.setContentType("application/json");
        if(!getSessionUser(request, response, json, "User")) {
        	System.out.println(json);
			response.getWriter().write(json.toString()); 
			return;
        }
	    JSONObject jsonData = getRequestBody(request, response, json);
	    if(jsonData==null || jsonData.isEmpty()) {
	    	System.out.println(json);
			response.getWriter().write(json.toString()); 
			return;
		}
		int userId = (Integer) json.get("userId");
		ERSUser user = null;
		try {
			user = new ERSUserDAO().getUserByUserId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(user==null) {
			json.put("problem", "Error: user does not exist in db");
			doDelete(request, response);
			return;
		}
	    String username = (String) jsonData.get("username");
		String password = (String) jsonData.get("password");
		String firstName=(String) jsonData.get("firstName");
		String lastName=(String) jsonData.get("lastName");
		String email=(String) jsonData.get("email");
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		try {			
			if(new ERSUserDAO().updateUser(user)) {
				json.put("update", "success");
			}else {
				json.put("update", "Error updating user information. try again");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			json.put("update", "Error updating user information. try again");
		}
		System.out.println("update user output json: " +json);
		response.getWriter().write(json.toString()); 
	}
	@SuppressWarnings("unchecked")
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Index doDelete");
		JSONObject json = new JSONObject();
		if(request.getSession().getAttribute("userId")==null) {
			System.out.println("no user found");
			request.getSession().invalidate();
			doGet(request, response);
			return;
		}
		System.out.println("logout successful");
		request.getSession().invalidate();
		json.put("logout", true);
        response.setContentType("application/json");
        response.getWriter().write(json.toString()); 
	}
	protected void login() {
		
	}
	@SuppressWarnings("unchecked")
	protected boolean getSessionUser(HttpServletRequest request, HttpServletResponse response, JSONObject json, String role)throws ServletException, IOException {
		HttpSession session = request.getSession();
	    Object obj = session.getAttribute("userId");
	    if(obj==null) {
			json.put("problem", "Error: no session user id found");
	    	return false;
	    }
	    Integer userId = (Integer) obj;
	    ERSUser user= null;
	    try {
			user = new ERSUserDAO().getUserByUserId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			json.put("problem", "Error: unable to find user in DB");
	    	return false;
		}
	    if(!user.getUserRole().equals(role) && !role.equals("User")){
	    	json.put("problem", "Error: user is not authorized");
	    	return false;
	    }
	    json.put("userId", user.getUserId());
		json.put("userRole", user.getUserRole());
		json.put("firstName", user.getFirstName());
		json.put("lastName", user.getLastName());
		json.put("email", user.getEmail());
	    System.out.println("Session: " + user);
	    System.out.println("Session: " + json);
	    return true;
	}
	@SuppressWarnings("unchecked")
	protected JSONObject getRequestBody(HttpServletRequest request, HttpServletResponse response, JSONObject json)throws ServletException, IOException {
	    JSONObject jsonData = null;
		String data = request.getReader().lines().collect(Collectors.joining());
	    System.out.println("data " + data);
	    if(data == null || data.isEmpty()) {
	    	json.put("problem", "no content recieved");
			return null;
	    }
	    JSONParser parser = new JSONParser();
	    try {
	    	jsonData= (JSONObject) parser.parse(data);
		} catch (ParseException e1) {
			e1.printStackTrace();
			json.put("problem", "unable to parse content");
			return null;
		}
	    if(jsonData.isEmpty()) {
	    	json.put("problem", "no json content recieved");
			return null;
	    }
	    System.out.println("request body data:" + jsonData);
	    return jsonData;
	}
	@SuppressWarnings("unchecked")
	protected void getAllReimbursementTypes(JSONObject json) {
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
	@SuppressWarnings("unchecked")
	protected void getAllEmployeeRoles(JSONObject json) {
		List<ERSUserRole> roles = null;
		try {
			roles = new ERSUserRoleDAO().getAllRoles();
		} catch (SQLException e) {
			e.printStackTrace();
			json.put("problem", "Error: unable to retrieve all user roles");
			return;
		}
		JSONArray array = new JSONArray();
		if(roles==null) {
			json.put("problem", "Error: unable to retrieve all user roles");
			return;
		}
		for(ERSUserRole role: roles) {
			JSONObject temp = new JSONObject();
			Gson gsonbuilder =new GsonBuilder().create();
			String bean = gsonbuilder.toJson(role);
			JSONParser parser = new JSONParser();
			try {
				temp =(JSONObject) parser.parse(bean);
				array.add(temp);
			} catch (ParseException e) {
				e.printStackTrace();
				json.put("problem", "Error: unaable to parse class to JSON");
			}
		}
		json.put("roles", array);
	}
	@SuppressWarnings("unchecked")
	protected void createReimbursement(JSONObject jsonData, JSONObject json) {
		String receipt =  (String) jsonData.get("receipt");
		System.out.println(receipt);
		byte[] decodedByte = Base64.getDecoder().decode(receipt.split(",")[1]);
		//byte[] decodedByte = Base64.getUrlDecoder().decode(receipt);
		System.out.println("bytes");
		System.out.println(decodedByte.toString());
		Blob receiptBlob = null;
		
		try {
			receiptBlob = new SerialBlob(decodedByte);
		} catch (SerialException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("blob");
		System.out.println(receiptBlob.toString());
		ERSReimbursement reimbursement = new ERSReimbursement ((Integer) json.get("userId"), ((Long) jsonData.get("amount")).doubleValue(),
				(String) jsonData.get("description"), decodedByte , ((Long) jsonData.get("type")).intValue()); 
		try {
			if( new ERSReimbursementDAO().addReimbursement(reimbursement)) {
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
