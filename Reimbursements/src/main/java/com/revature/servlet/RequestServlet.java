package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.reimbursements.Reimbursement;
import com.revature.reimbursements.ReimbursementServices;
import com.revature.reimbursements.UserOn;

public class RequestServlet extends HttpServlet {
	ReimbursementServices rs = new ReimbursementServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("HI");
		
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		resp.sendRedirect("/Reimbursements/RequestReimbursement.html");
		//resp.setContentType("text/html");
		//req.getRequestDispatcher("Login.html").include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login DO POST");
		HttpSession session = req.getSession();

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		double amt = Double.parseDouble(req.getParameter("amount"));
		String deez = req.getParameter("description");
		String nutz = req.getParameter("receipt");
		int type = Integer.parseInt(req.getParameter("type"));
		System.out.println("type " + type);
		Reimbursement r = new Reimbursement();
		r.setR_amount(amt);
		r.setR_description(deez);
		r.setRt_type(type);
		int u = (int)req.getSession(false).getAttribute("userID");
		r.setU_id_author(u);
		try {
			rs.createReimbursement(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("/EmployeeHome");
//
//		System.out.println(username+" "+password);
//		
//		UserOn valid = login(username, password);
//		if (valid != null)
//		{
//			session.setAttribute("username", username);
//			session.setAttribute("userID", valid.getU_id());
//			session.setAttribute("roleID", valid.getUr_id());
//			if (valid.getUr_id() == 1)
//			{
//				resp.sendRedirect("./ManagerHome.html");
//			}else {
//				resp.sendRedirect("./EmployeeHome.html");
//			}
//				
//			resp.setStatus(211);
//		}
//		else {
//			resp.getWriter().write("login flails");
//			resp.setStatus(401);
//		}

	}
}

