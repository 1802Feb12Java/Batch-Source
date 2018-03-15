package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.revature.DAOUtilities;
import com.revature.EmployeeDAO;
import com.revature.beans.User;

import jdk.internal.jline.internal.Log;

import org.apache.log4j.*;

public class SubmitRequestServlet extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(SubmitRequestServlet.class);
	
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		EmployeeDAO eDao = DAOUtilities.getEmpDAO();
		
		int userid = (Integer)session.getAttribute("u_id");
		
		double amount = Double.parseDouble(request.getParameter("amount"));
		String descrip = request.getParameter("descrip");
		int type = Integer.parseInt(request.getParameter("type"));
		
		String pic = request.getParameter("pic");
		
		try {
			eDao.submitRequest(amount, descrip, userid, type);
			log.info("submitted request");
			response.sendRedirect("EmpPendReqs.html");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
