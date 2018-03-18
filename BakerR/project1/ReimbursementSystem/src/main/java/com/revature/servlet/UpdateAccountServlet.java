package com.revature.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.revature.bean.User;
import com.revature.dao.UserDao;

/**
 * Servlet implementation class UpdateAccountServlet
 */
public class UpdateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		ServletContext ctx = getServletContext();
		File webinfPath = new File(ctx.getRealPath("WEB-INF"));
		User user;
		Document basePage = null
			   , fragPage = null;
		String type = request.getParameter("type");
		
		// Determine requester
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		user = (User)(session.getAttribute("loggeduser"));
		
		// Get base page.
		if(user.getRole().getRole().equalsIgnoreCase("manager")) {
			basePage = Jsoup.parse(new File(webinfPath, "manager-base.html"), null);
		} else if(user.getRole().getRole().equalsIgnoreCase("employee")) {
			basePage = Jsoup.parse(new File(webinfPath, "employee-base.html"), null);
		} else {
			response.sendRedirect("logout");
			return;
		}
		
		// Set username in greeting field.
		basePage.getElementById("user").empty().appendText(user.getUsername());
		
		
		// Get page fragment.
		if(type == null) {
			response.sendRedirect("home");
			return;
		} if(type.equalsIgnoreCase("info")) {
			fragPage = Jsoup.parse(new File(webinfPath, "update-acct-info.fragment.html"), null);
		} else if(type.equalsIgnoreCase("pass")) {
			fragPage = Jsoup.parse(new File(webinfPath, "update-acct-pass.fragment.html"), null);
			
			// Insert needed javascript into page.
			basePage.body().appendElement("script")
						   .attr("src", "resources/update-pass.js");
		} else {
			response.sendRedirect("home");
			return;
		}
		
		
		// Combine base & frag
		Elements frags = fragPage.getElementById("fragment").children();
		basePage.getElementById("content").append(frags.outerHtml());
		
		// Add stylesheet for fragment
		basePage.body().appendElement("link").attr("rel", "stylesheet")
											 .attr("href", "resources/register.css");
		
		// Write html to writer.
		response.setContentType("text/html");
		response.getWriter().println(basePage.outerHtml());
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
		
		HttpSession session = request.getSession(false);
		User user;
		String type = request.getParameter("type");
		
		// Check for valid user.
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		// Get logged user
		user = (User)(session.getAttribute("loggeduser"));
		
		try {
			UserDao userDao = new UserDao();
			
			if(type == null) {
				response.sendRedirect("home");
				return;
			} else if(type.equalsIgnoreCase("info")) {
				// Perform user info update
				
				// Get info to update.
				User newInfo = new User();
				String info;
				
				info = request.getParameter("firstname"); 
				newInfo.setFirstName(info == null || info.isEmpty() ? 
						null : info);
				
				info = request.getParameter("lastname"); 
				newInfo.setLastName(info == null || info.isEmpty() ? 
						null : info);
				
				info = request.getParameter("email"); 
				newInfo.setEmail(info == null || info.isEmpty() ? 
						null : info);
				
				// Update info.
				if(userDao.updateUser(user.getId(), newInfo)) {
					// Get new info.
					user = userDao.getUserById(user.getId());
					session.setAttribute("loggeduser", user);
					
					response.sendRedirect("home");
					return;
				} else {
					response.sendRedirect("update?type=info");
					return;
				}
			} else if(type.equalsIgnoreCase("pass")) {
				// Perform user password update
				
				// Get password
				String currentPw = request.getParameter("current-password");
				String newPw = request.getParameter("new-password");
				String confirmPw = request.getParameter("confirm-new-password");
				
				if(currentPw == null || newPw == null || confirmPw == null ||
						currentPw.isEmpty() || newPw.isEmpty() || confirmPw.isEmpty()) {
					// any field is null/empty
					response.sendRedirect("update?type=pass");
					return;
				} else if(!newPw.equals(confirmPw)) {
					// new password doesn't match with confirmation 
					response.sendRedirect("update?type=pass");
					return;
				} else if(userDao.verifyPassword(user.getUsername(), currentPw)) {
					// new password confirmed & user verified
					userDao.updatePassword(user.getId(), newPw);
					response.sendRedirect("home");
					return;
				} else {
					// Incorrect password.
					response.sendRedirect("update?type=pass");
					return;
				}
			} else {
				// Unmatched type.
				response.sendRedirect("home");
				return;
			}
		} catch(SQLException | IOException ex) {
			response.sendRedirect("home");
			return;
		}
	}
	
	
	
}
