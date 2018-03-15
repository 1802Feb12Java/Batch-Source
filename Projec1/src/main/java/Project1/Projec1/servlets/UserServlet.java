package Project1.Projec1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Project1.Projec1.UserDAO.UserDaoImple;
import Project1.Projec1.UserRolesDao.UserRolesDaoImple;
import Project1.Projec1.pojo.UserRoles;
import Project1.Projec1.pojo.Users;

@WebServlet("/usersservlet")

public class UserServlet extends HttpServlet{
	private Logger logger = Logger.getLogger(UserServlet.class);
	private static final long serialVersionUID = 1L;

	//login button
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		UserDaoImple dao = new UserDaoImple();
		 UserRolesDaoImple roleDao = new UserRolesDaoImple();
		try {
			logger.info("Post is working");
			//ArrayList<Users> userList = dao.getAllUsers();//retrieve users login
			//use session to login
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			String username = req.getParameter("username");
			String password = req.getParameter("pwd");
			boolean isValid = dao.checkUser(username, password);
		
			//check login credentials
			Users user = dao.getOneUser(username);
			UserRoles role = roleDao.getOneUserRole(user.getUr_id());
			boolean ad = roleDao.checkAdmin(user.getU_id());
			logger.info(role.toString());
			logger.info(ad);
				//if credentials match, redirects to main page
			if(isValid) {
				HttpSession session = req.getSession();

				//if user is an admin , goes to admin page
				if(roleDao.checkAdmin(user.getU_id())){
					logger.info(user.toString());
					req.getSession().setAttribute("user", user); //get user in session
					out.write("<div class=\"alert alert-success\" role=\"alert\">");
					out.write("Logged in successfully");
					out.write("</div>");
			//		resp.sendRedirect("adminlogin.html");
			        req.getRequestDispatcher("adminlogin.html").include(req, resp);  
			        out.write("<p> Hello " + user.getU_firstname() + "</p><br>");
					out.write("What would you want to do today?");

					logger.info("logged in as admin");

				}
				else {
					//if user is a regular user, goes to user page
					logger.info("logged in as user");
					logger.info(user.toString());
					req.getSession().setAttribute("user", user); //get user in session
					logger.info(session.getId());
						//resp.sendRedirect("successfulLogin.html");
					out.write("<div class=\"alert alert-success\" role=\"alert\">");
					out.write("Logged in successfully");
					out.write("</div>");
			        req.getRequestDispatcher("successfulLogin.html").include(req, resp);  
			    	out.write("<p> Hello " + user.getU_firstname() + "</p><br>");
					out.write("What would you want to do today?");
				}
			}
				//if login is invalid, goes back to login page
				else {
					out.write("<div class=\"alert alert-danger\" role=\"alert\">");
					out.write("Invalid username/password");
					out.write("</div>");
					//return to login page
					req.getRequestDispatcher("login.html").include(req, resp);  
								
				}
				out.close();//close writer
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	//logout button
	@Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  		  
		logger.info("logout get servlet is running");
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		out.write("<div class=\"alert alert-success\" role=\"alert\">");
		out.write("Logged out successfully");
		out.write("</div>");
		req.getRequestDispatcher("login.html").include(req, resp);  

		//req.getRequestDispatcher("login.html").include(req, resp);
		HttpSession session = req.getSession(false);
		if (session != null){
			session.invalidate();//cancels session and logged off
		}
		logger.info(session.getId());
		
		//out.write("<font color=green>"+" You logged out successfully!" + "</font>");
		
		out.close();
	}
}
