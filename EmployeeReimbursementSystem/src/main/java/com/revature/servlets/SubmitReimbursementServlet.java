package com.revature.servlets;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.dao.UserDAOImpl;

@MultipartConfig

public class SubmitReimbursementServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4421700574112052267L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{	
		
		User user = (User) req.getSession().getAttribute("user");
		Reimbursement reimbursement = new Reimbursement();
		ReimbursementDAOImpl reimbursementDao = new ReimbursementDAOImpl();
		
		//reimbursement.setReciept("NONE");
		reimbursement.setAmount(Double.parseDouble(req.getParameter("amount")));
		reimbursement.setDescription(req.getParameter("description"));
		
		System.out.println("UserID: "+ user.getUserID());
		
//		try {
//			Part filePart = req.getPart("file");
//
//			InputStream fileContent = filePart.getInputStream();
//			reimbursement.setReciept(fileContent);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		reimbursement.setUserIDAuthor(user.getUserID());
		reimbursement.setUserIDResolver(0);
		reimbursement.setReimbursementTypeID(Integer.parseInt(req.getParameter("types")));
		reimbursement.setReimbursementStatusID(0);		
		
		reimbursementDao.addReimbursementNoImage(reimbursement);
		
		try {
			resp.sendRedirect("./EmployeeHome.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}