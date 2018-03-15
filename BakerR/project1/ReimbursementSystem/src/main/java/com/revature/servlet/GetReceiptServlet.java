package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.bean.Reimbursement;
import com.revature.dao.ReimbursementDao;

/**
 * Servlet implementation class GetReceiptServlet
 */
public class GetReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReceiptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 
		 if(session == null) {
			 response.sendRedirect("login");
			 return;
		 }
		 
		 // Get reimbursement id
		 String rIdStr = request.getParameter("id");
		 if(rIdStr == null) {
			 response.sendRedirect("home");
			 return;
		 }
		 int rId = 0;
		 
		 try {
			 rId = Integer.parseInt(rIdStr);
		 } catch(NumberFormatException ex) {
			 response.sendRedirect("home");
			 return;
		 }
		 
		 
		 Reimbursement r = null;
		 try {
			 ReimbursementDao rDao = new ReimbursementDao();
			 r = rDao.getReceipt(rId);
			 
			 if(r == null || r.getReceiptName() == null || r.getReceipt() == null) {
				 response.sendRedirect("home");
				 return;
			 }
			 
			 Map.Entry<String, Integer> meta = rDao.getReceiptMetadata(rId);
			 
			 response.setContentType("application/octet-stream");
			 response.setHeader("Content-Disposition", "attachment; filename=\"" + r.getReceiptName() + "\"");
			 
			 response.getOutputStream().write(r.getReceipt());
			 
		 } catch(SQLException | IOException ex) {
			 ex.printStackTrace();
//			 response.sendRedirect("home");
//			 return;
		 }
	}

}
