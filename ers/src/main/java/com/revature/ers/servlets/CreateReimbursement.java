package com.revature.ers.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.revature.ers.reimbursements.Reimbursement;
import com.revature.ers.reimbursements.ReimbursementServices;

/**
 * Servlet implementation class CreateReimbursement
 */
public class CreateReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReimbursement() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger log = Logger.getRootLogger();
		ReimbursementServices rs = new ReimbursementServices();
		Reimbursement reim = new Reimbursement();

		//parse the string into a JSON object
		String jString = request.getReader().readLine();
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(jString);
			
			//get the data in string form
			String amount = (String) json.get("amount");
			String selection =  (String) json.get("selected");
		    String description = (String) json.get("description");
		    
		    //translate the strings into values
			Integer u_ID = (Integer) request.getSession(false).getAttribute("userID");		    
		    double amt = Double.parseDouble(amount);
		    int option = Integer.parseInt(selection);

		    //add the values to the reimbursement object
		    reim.setR_amount(amt);
		    reim.setR_description(description);
		    reim.setRt_Type(option);
		    reim.setU_ID_Author(u_ID.intValue());
		    rs.addReimbursement(reim);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
