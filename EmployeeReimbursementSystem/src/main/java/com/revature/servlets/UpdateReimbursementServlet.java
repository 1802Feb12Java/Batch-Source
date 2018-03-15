package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.dao.UserDAOImpl;

public class UpdateReimbursementServlet extends HttpServlet{
	
	private static final long serialVersionUID = -1320697891363873277L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		System.out.println("get>>>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{

		UserDAOImpl userDAO = new UserDAOImpl();
		User user = (User) req.getSession(false).getAttribute("user");
		Reimbursement reimbursement = new Reimbursement();
		ReimbursementDAOImpl reimbursementDao = new ReimbursementDAOImpl();
		
		System.out.println("test");

		try {
			Integer r_id = Integer.parseInt(req.getParameter("approve"));
			
			System.out.println(r_id);

			reimbursement = reimbursementDao.getReimbursementByID(r_id);

			reimbursement.setReimbursementStatusID(1);
			reimbursement.setUserIDResolver(user.getUserID());
			
			reimbursementDao.updateReimbursement(reimbursement);
		}catch(NumberFormatException e){

			Integer r_id = Integer.parseInt(req.getParameter("deny"));
			
			reimbursement = reimbursementDao.getReimbursementByID(r_id);
			
			reimbursement.setReimbursementStatusID(2);

			reimbursement.setUserIDResolver(user.getUserID());
			reimbursementDao.updateReimbursement(reimbursement);
		}
		
//		user.setUsername(req.getParameter("usernameText")); 
//		user.setPassword(req.getParameter("passwordText"));
//		user.setFirstName(req.getParameter("firstName"));
//		user.setLastName(req.getParameter("lastName"));
//		user.setEmail(req.getParameter("email"));
//		
//		userDAO.updateUser(user);
//		req.getSession().setAttribute("user", user);
		
		try {
			resp.sendRedirect("./ManagerReimbursementView.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
