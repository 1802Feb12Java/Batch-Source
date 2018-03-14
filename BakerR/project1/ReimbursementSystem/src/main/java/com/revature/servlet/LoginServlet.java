package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.bean.User;
import com.revature.dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			request.getRequestDispatcher("WEB-INF/login.html").forward(request, response);
		} else {
			response.sendRedirect("home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		User user;
		
		// Check for active session.
		if(session != null) {
			response.sendRedirect("home");
			return;
		}
		
		// No active session.
		try {
			// Verify Username/Login Combo
			boolean verified = false;
			UserDao usrDao = new UserDao();
			
			String username = request.getParameter("username"); 
			
			verified = usrDao.verifyPassword(
					username,
					request.getParameter("password"));
		
			// Create http session
			if(verified) {
				// successful login -> load user & set into session.
				user = usrDao.getUserByUsername(username);
				session = request.getSession();
				session.setMaxInactiveInterval(1800);
				session.setAttribute("loggeduser", user);
				response.sendRedirect("home");
			} else {
				// Failed login -> redirect to login again.
				response.sendRedirect("login");
			}
		} catch (SQLException | IOException ex) {
			out.println(ex.getMessage());
			for(StackTraceElement e : ex.getStackTrace()) {
				out.println("    " + e.toString());
			}
		}
	}
	
	
}
