package com.revature.ers.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.dao.ERSReimbursementsDAO;
import com.revature.ers.model.Reimbursements;

/**
 * Servlet implementation class AddReimbursementServlet
 */
public class AddReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("employeePages/eReimbursementForm.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String r_amount = request.getParameter("r_Amount");
		String r_Description = request.getParameter("r_Description");
		String r_receipt = request.getParameter("r_receipt");
		String r_type = request.getParameter("r_type");
		String u_id_author = request.getParameter("u_id_author");
		
		Reimbursements re = new Reimbursements();
		
//		ps.setDouble(1, re.getR_amount());
//		ps.setString(2, re.getDescription());
//		ps.setBlob(3, re.getR_receipt());
//		ps.setTimestamp(4, re.getR_submitted());
//		ps.setTimestamp(5, re.getR_resolved());
//		ps.setInt(6, re.getU_id_author());
//		ps.setInt(7, re.getU_id_resolver());
//		ps.setInt(8, re.getRt_type());
//		ps.setInt(9, re.getRt_status());
		
		re.setR_amount(Double.parseDouble(r_amount));
		re.setDescription(r_Description);
		re.setR_receipt(null);
		re.setR_resolved(null);
		re.setU_id_author(Integer.parseInt(u_id_author));
		re.setU_id_resolver(0);
		re.setRt_type(Integer.parseInt(r_type));
		re.setRt_status(1);
		
		ERSReimbursementsDAO reimbursements = new ERSReimbursementsDAO();
		try {
			reimbursements.submitReimbursements(re);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("employeePages/employeeHomepage.jsp").forward(request, response);
	}

}
