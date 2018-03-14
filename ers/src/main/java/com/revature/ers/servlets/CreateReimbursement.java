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

import com.revature.ers.jsonifiers.ParseJSON;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//parse the object into JSON
		Logger log = Logger.getRootLogger();
		ReimbursementServices rs = new ReimbursementServices();
		Reimbursement reim = new Reimbursement();

		String jString = request.getReader().readLine();
		System.out.println(jString);
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(jString);
			String amount = (String) json.get("amount");
			String selection =  (String) json.get("selected");
		    String description = (String) json.get("description");
			Integer u_ID = (Integer) request.getSession(false).getAttribute("userID");		    
		    double amt = Double.parseDouble(amount);
		    int option = Integer.parseInt(selection);

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
		


//		reim.setU_ID_Author(u_ID.intValue());
//		reim.setR_amount(amount.doubleValue());
//		reim.setR_description((String) employee.get("description"));
//		reim.setRt_Type(selection.intValue());
//		
//		try {
//			rs.addReimbursement(reim);
//		} catch (SQLException e) {
//			log.error("Unable to contact the database.");
//		}		
	}
}
