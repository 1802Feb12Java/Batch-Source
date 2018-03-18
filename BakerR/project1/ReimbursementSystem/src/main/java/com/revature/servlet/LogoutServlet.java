package com.revature.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
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
		if(session != null) {
			session.invalidate();
			response.sendRedirect("login");
		} else {
			response.sendRedirect("login");
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
		
		doGet(request, response);
	}

}
