package com.revature.ers.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.dao.ERSReimbursementsDAO;
import com.revature.ers.model.Reimbursements;

/**
 * Servlet implementation class UpdateStatusServlet
 */
public class UpdateStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            //get parameters from the form
            String r_id = request.getParameter("r_id");

            request.setAttribute("reimbursement", new ERSReimbursementsDAO().selectReimbursements(Integer.parseInt(r_id)));
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //forward request to the response
        request.getRequestDispatcher("managerPages/mReimburseFormUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. get parameters
		String re_id = request.getParameter("re_id");
		String resolverID = request.getParameter("u_id_resolver");
		String r_status = request.getParameter("r_status");
		
		try {
			ERSReimbursementsDAO reimbursement = new ERSReimbursementsDAO();
			
			Reimbursements re = new ERSReimbursementsDAO().selectReimbursements(Integer.parseInt(re_id));

			re.setU_id_resolver(Integer.parseInt(resolverID));
			re.setRt_status(Integer.parseInt(r_status));
			
			reimbursement.updateReimbursement(re);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		request.getRequestDispatcher("managerPages/managerHomepage.jsp").forward(request, response);
	}

}
