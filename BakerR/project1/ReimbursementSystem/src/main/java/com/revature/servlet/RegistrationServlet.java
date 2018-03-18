package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.bean.User;
import com.revature.bean.UserRole;
import com.revature.dao.UserDao;
import com.revature.dao.UserRoleDao;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties servletProps = ServletProperties.getProperties();
		
		if(servletProps != null) {
			request.setCharacterEncoding(servletProps.getProperty("request.enc"));
			response.setCharacterEncoding(servletProps.getProperty("response.enc"));
		}
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			response.sendRedirect("home");
		} else {
			request.getRequestDispatcher("WEB-INF/register.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties servletProps = ServletProperties.getProperties();
		
		if(servletProps != null) {
			request.setCharacterEncoding(servletProps.getProperty("request.enc"));
			response.setCharacterEncoding(servletProps.getProperty("response.enc"));
		}
		
		// Create new user.
		try {
			UserDao userDao = new UserDao();
			UserRoleDao urDao = new UserRoleDao();
			
			User user = new User();
			String username = request.getParameter("username");
			user.setUsername(username);
			user.setFirstName(request.getParameter("firstname"));
			user.setLastName(request.getParameter("lastname"));
			user.setEmail(request.getParameter("email"));
			UserRole ur = urDao.getUserRoleById(Integer.parseInt(request.getParameter("usr-role")));
			user.setRole(ur);
			
			
			boolean userAdded = userDao.addUser(user, request.getParameter("password"));
			
			if(userAdded) {
				// New user created -> redirect to login.
				response.sendRedirect("login");
			} else {
				response.sendRedirect("register");
			}
		} catch(SQLException | IOException ex) {
			ex.printStackTrace(response.getWriter());
		}
	}
	
	
}
