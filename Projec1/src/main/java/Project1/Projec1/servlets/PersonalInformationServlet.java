package Project1.Projec1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Project1.Projec1.UserDAO.UserDaoImple;
import Project1.Projec1.UserRolesDao.UserRolesDaoImple;
import Project1.Projec1.pojo.Users;

@WebServlet("/piservlet")

public class PersonalInformationServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(PersonalInformationServlet.class);
	private static final long serialVersionUID = 1L;
	//----------------- Display user information
	@Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
	            throws ServletException, IOException {  
		UserDaoImple dao = new UserDaoImple();

		logger.info("Post is working");
		//create a json object
		//JSONObject json = new JSONObject();
		ObjectMapper json = new ObjectMapper();
		//use session to login
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		//check login credentials
		Users user = new Users();
		user = (Users) req.getSession(false).getAttribute("user"); // get current user

		logger.info("current user is " + user.getU_firstname());
		if(user != null) {
			try {
				user = dao.returnOneUser(user.getU_id());//get new data

				//convert object to json string
				String jsonInString = json.writeValueAsString(user);
				logger.info(jsonInString);
				out.print(jsonInString);
				
			}catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		else {
			out.println("Invalid User! Please login");
			req.getRequestDispatcher("login.html").include(req, resp);  

		}
//			logger.info(user.toString());
//			session.setAttribute("username", username);
//			logger.info(session.getId());  
//			out.print(user.toString());
		//	out.println("Hello " + user.getU_firstname());
			out.write("\n");
//			out.write("What would you want to do today?");
			

			
	 }
	// post - update forms
	@Override
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
	            throws ServletException, IOException { 
		
		try {
			resp.setContentType("text/html");
			logger.info("piservlet post is running");
			Users user = (Users) req.getSession(false).getAttribute("user"); // get current user
		
			String username = user.getU_username();
			UserDaoImple dao = new UserDaoImple();
			UserRolesDaoImple roleDao = new UserRolesDaoImple();
		
			PrintWriter out = resp.getWriter();
			String oldpass = req.getParameter("oldpass");
			String newpass = req.getParameter("newpass");
			String fname = req.getParameter("fname");
			String lname = req.getParameter("lname");
			String email = req.getParameter("email");
			logger.info(fname);
			logger.info(lname);
			logger.info(email);
			logger.info(oldpass);
			logger.info(newpass);
			boolean admin = roleDao.checkAdmin(user.getU_id());
			if(newpass != null) {
				if(dao.checkUser(username, oldpass)) {
					dao.updatePass(newpass,username);
					user = dao.returnOneUser(user.getU_id());//get new data
					out.write("<div class=\"alert alert-success\" role=\"alert\">");
					out.write("Password changed successfully");
					out.write("</div>");
					if(admin) {
						req.getRequestDispatcher("admininfo.html").include(req, resp); 

					}
					else {
						req.getRequestDispatcher("personalinfo.html").include(req, resp); 
					}
					
				}
				else {
					out.write("<div class=\"alert alert-danger\" role=\"alert\">");
					out.write("Old password is wrong");
					out.write("</div>");
					if(admin) {
						req.getRequestDispatcher("admininfo.html").include(req, resp); 

					}
					else {
						req.getRequestDispatcher("personalinfo.html").include(req, resp); 
					}

				}
				
			}
			
		
			if(lname != null) {
				dao.updateLname(lname,username);
				user = dao.returnOneUser(user.getU_id());//get new data
				out.write("<div class=\"alert alert-success\" role=\"alert\">");
				out.write("Last name changed successfully");
				out.write("</div>");
				if(admin) {
					req.getRequestDispatcher("admininfo.html").include(req, resp); 

				}
				else {
					req.getRequestDispatcher("personalinfo.html").include(req, resp); 
				}

			}
			if(fname != null) {
				dao.updateFname(fname,username);
				logger.info("first name changed" );
				user = dao.returnOneUser(user.getU_id());//get new data
				
				out.write("<div class=\"alert alert-success\" role=\"alert\">");
				out.write("First name changed successfully");
				out.write("</div>");
				if(admin) {
					req.getRequestDispatcher("admininfo.html").include(req, resp); 

				}
				else {
					req.getRequestDispatcher("personalinfo.html").include(req, resp); 
				}

			}
			if(email != null) {
				dao.updateEmail(email,username);
				
				user = dao.returnOneUser(user.getU_id());//get new data

				out.write("<div class=\"alert alert-success\" role=\"alert\">");
				out.write("Email changed successfully");
				if(admin) {
					req.getRequestDispatcher("admininfo.html").include(req, resp); 

				}
				else {
					req.getRequestDispatcher("personalinfo.html").include(req, resp); 
				}

			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
