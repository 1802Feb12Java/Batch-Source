package com.revature.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.Users;
import com.revature.beans.User;

public class LoginServlet extends HttpServlet {

	private static final Logger logger = LogManager.getLogger(LoginServlet.class);

	private static final long serialVersionUID = -9109911146582857848L;

	private Connection con = com.revature.database.ConnectionFactory.getInstance().getConnection();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/ERSApp/login.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		logger.info("Got a request " + request.getRequestURL());

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String n = request.getParameter("username");
		String p = request.getParameter("userpass");
		logger.info("got username: " + n + ", password " + p);
		// DAO
		User u = Users.getUser(con, n, p);

		if (u != null) {
			logger.info("Logged in user " + u);
			request.getSession().setAttribute("user", u); // Put user in session.
			// checks user type and sends them to correct homepage
			if (u.getUserRole().toLowerCase().equals("admin") || u.getUserRole().toLowerCase().equals("manager")) {
				response.sendRedirect("./admin/home.html");
			} else {
				response.sendRedirect("./secure/home.html"); // Go to some start page.
			}
		} else {
			logger.info("Unable to log user in");

			// MANUAL SEND BACK
			response.setContentType("text/html");

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("login.html");

			StringWriter writer = new StringWriter();
			IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8);
			String loginPage = writer.toString();

			out.print(loginPage);

		}

		out.close();
	}

	@Override
	public void destroy() {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	}

}
