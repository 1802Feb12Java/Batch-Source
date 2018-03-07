package com.revature.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.factory.ConnectionFactory;

public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class.toString());
	private static ConnectionFactory conn = ConnectionFactory.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Get Better Roll Tide");
		log.info("Getting, mothafucka");

		PreparedStatement ps;
		try {
			ps = conn.getConnection().prepareStatement("Select * from ers_user_roles");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				System.out.println("----------------New role----------------");
				System.out.println("User id = " + rs.getInt(1));
				System.out.println("User role = " + rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Post Better Roll Tide");
	}
	
}
