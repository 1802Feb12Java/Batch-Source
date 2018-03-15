package com.revature.ers.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.revature.ers.dao.ERSReimbursementsDAO;
import com.revature.ers.model.Reimbursements;


/**
 * Servlet implementation class ViewAllReimbursements
 */
public class AllReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Reimbursements> reim = new ArrayList<Reimbursements>();
		try {
			reim = new ERSReimbursementsDAO().getAllReimbursements();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject();
		JSONArray reimList = new JSONArray();
		
		/**
		 * set property values for JSON user object
		 * put each users in a JSON container and each index will represent a user and their properties
		 * add all users to the array
		 */
		System.out.println("get reim");
		System.out.println(reim);
		for(int i = 0; i < reim.size(); i++) {
			JSONObject reimSend = new JSONObject();
			reimSend.put("ID", reim.get(i).getR_id());
			reimSend.put("amount", reim.get(i).getR_amount());
			reimSend.put("description", reim.get(i).getDescription());
			reimSend.put("receipt", reim.get(i).getR_receipt());
			
			reimSend.put("reimType", reim.get(i).getRt_type());
			reimSend.put("reimStatus", reim.get(i).getRt_status());
			
			reimSend.put("timeSubmitted", reim.get(i).getR_submitted().toString());
			//check if there is a null resolved time
			Timestamp temp =reim.get(i).getR_resolved();
			reimSend.put("timeResolved", (temp==null) ? "": temp.toString());
			
			
			reimSend.put("authorID", reim.get(i).getU_id_author());
			reimSend.put("author_firstname", reim.get(i).getAuthor_firstname());
			reimSend.put("author_lastname", reim.get(i).getAuthor_lastname());
			
			reimSend.put("managerID", reim.get(i).getU_id_resolver());
			reimSend.put("resolver_firstName", reim.get(i).getResolver_firstname());
			reimSend.put("resolver_lastname", reim.get(i).getResolver_lastname());
			
			reimList.add(reimSend);
		}
		json.put("reimbursementList", reimList);
		response.setContentType("application/json");
        PrintWriter pw = response.getWriter(); 
        pw.print(json.toString());
        pw.close();
	} 
	
}
