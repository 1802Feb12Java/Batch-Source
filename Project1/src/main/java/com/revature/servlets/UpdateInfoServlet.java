package com.revature.servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.DAOs.ReimbursementDAOClass;
import com.revature.DAOs.UserDAOClass;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.util.ConnectionFactory;
import com.revature.util.FrontController;

public class UpdateInfoServlet extends HttpServlet {
	private ConnectionFactory cf = ConnectionFactory.getInstance();
	private Connection conn = cf.getConnection();
	private UserDAOClass userDao = new UserDAOClass(conn);
	private static final Logger log = Logger.getLogger(UpdateInfoServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.service(req, resp);
//        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//        resp.addHeader("Access-Control-Allow-Headers",
//                "Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
//        resp.addHeader("Access-Control-Allow-Credentials", "true");
//        resp.setContentType("application/json");
//	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		FrontController.addHeader(resp);
		resp.setContentType("text/html");
		
		int userID = (Integer) session.getAttribute("uid");
		String firstname = new String(req.getParameter("firstname"));
		String lastname = new String(req.getParameter("lastname"));
		String username = new String(req.getParameter("username"));
		String password = new String(req.getParameter("password"));
		String email = new String(req.getParameter("email"));
		
		ArrayList<User> allUsers = null;
		try {
			allUsers = userDao.getAllUsers();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		User currentUser = null;
		for(User u : allUsers) {
			if(u.getId() == userID) {
				currentUser = u;
			}
		}
		
		try {
			if(!firstname.equals(currentUser.getFirstname())) {
				userDao.updateFirstname(firstname, userID);
				log.info("Changed user "+userID+"'s first name to " + firstname);
			}
			if(!lastname.equals(currentUser.getLastname())) {
				userDao.updateLastname(lastname, userID);
				log.info("Changed user "+userID+"'s last name to " + lastname);
			}
			if(!username.equals(currentUser.getUsername())) {
				userDao.updateUsername(username, userID);
				log.info("Changed user "+userID+"'s username to " + username);
			}
			if(!password.equals(currentUser.getPassword())) {
				userDao.updatePassword(password, userID);
				log.info("Changed user "+userID+"'s password to " + password);
			}
			if(!email.equals(currentUser.getEmail())) {
				userDao.updateEmail(email, userID);
				log.info("Changed user "+userID+"'s email to " + email);
			}
			resp.sendRedirect("http://localhost:4200/employeeHome");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
