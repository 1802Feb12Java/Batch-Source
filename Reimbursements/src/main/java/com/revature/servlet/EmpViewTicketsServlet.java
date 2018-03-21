package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.reimbursements.Reimbursement;
import com.revature.reimbursements.ReimbursementServices;

/**
 * Servlet implementation class EmpViewTicketsServlet
 */
@WebServlet("/EmpViewTicketsServlet")
public class EmpViewTicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReimbursementServices us = new ReimbursementServices();
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpViewTicketsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);	
		int toget = (int) session.getAttribute("userID"); 
		ArrayList<Reimbursement> u = null;
		System.out.println("toget" + toget);
		try {
			u = us.getAll(toget);//is returning all instead of just the ones with passed id.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray jArr = new JSONArray();
        
        PrintWriter pw = response.getWriter();

        for(int i = 0; i < u.size(); i++) {
        	JSONObject job = new JSONObject();
            job.append("R_ID", u.get(i).getR_id());
            job.append("R_AMOUNT", u.get(i).getR_amount());
            job.append("R_DESCRIPTION", u.get(i).getR_description());
            //job.append("R_RECEIPT", u.get(i).getR_receipt());
            job.append("R_SUBMITTED", u.get(i).getR_submitted());
            job.append("R_RESOLVED", u.get(i).getR_resolved());
            job.append("U_ID_AUTHOR", u.get(i).getU_id_author());
            job.append("U_ID_RESOLVER", u.get(i).getU_id_resolver());
            job.append("RT_TYPE", u.get(i).getRt_type());
            job.append("RT_STATUS", u.get(i).getRt_status());
            //add the json obj to the array to send out
            jArr.put(i, job);
        }
        System.out.println("JARR");
        System.out.println(jArr.toString());
       response.getWriter().write(jArr.toString());
       response.sendRedirect("/Reimbursements/EmpView.html");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
