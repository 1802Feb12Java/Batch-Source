package com.revature.one.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.revature.one.beans.User;
import com.revature.one.dao.impl.UserDaoImpl;

/**
 * Servlet implementation class userServlet2
 */
public class userServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		User user = null;
		UserDaoImpl ud = new UserDaoImpl();
		String req = request.getQueryString();
		System.out.println(req);

		int id = Integer.parseInt(req.substring(req.indexOf('=') + 1, req.length()));
		System.out.println("id=" + id);
		try {
			user = ud.getUser(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject ju = new JSONObject(user);

		response.setContentType("text/json");
		try {
			response.getWriter().write(ju.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		UserDaoImpl ud = new UserDaoImpl();
		String req = request.getQueryString();
		System.out.println(req);

		int id = Integer.parseInt(req.substring(req.indexOf('=') + 1, req.lastIndexOf('?')));
		String name = (req.substring(req.lastIndexOf('=') + 1, req.length()));
		System.out.println("id=" + id);
		System.out.println("name=" + name);
		user.setId(id);
		user.setName(name);

		try {
			ud.addUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// StringBuffer jb = new StringBuffer();
		// String line = null;
		// User user = null;
		// try {
		// BufferedReader reader = request.getReader();
		// while ((line = reader.readLine()) != null)
		// jb.append(line);
		// } catch (Exception e) {
		// /* report an error */ }
		//
		// try {
		// JSONObject jsonObject = HTTP.toJSONObject(jb.toString());
		// user.setId(jsonObject.getInt("ID"));
		// user.setName(jsonObject.getString("Name"));
		// } catch (JSONException e) {
		// // crash and burn
		// throw new IOException("Error parsing JSON request string");
		// }
		//
		// // TODO Auto-generated method stub
		// doGet(request, response);
	}

}
