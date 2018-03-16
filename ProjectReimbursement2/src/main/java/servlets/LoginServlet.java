package servlets;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.User;
import pov.ReimFunc;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println("Reached: " + username + password);

		ReimFunc delegate = new ReimFunc();
		User user;

		try {
			user = delegate.login(username, password);

			System.out.println("Reached start of Login Controller for user: " + user.getFirst_name());

			if (user != null && user.getRole_id().getId() == 2) {
				System.out.println("Login controller redirect for type: " + user.getRole_id().getUser_role());

				request.getSession().setAttribute("user", user);
				System.out.println("Login Controller: successfuly got user session for User: " + user);

				response.sendRedirect("EmpHome.html");
			} else if (user != null && user.getRole_id().getId() == 1) {
				System.out.println("Login controller redirect for type: " + user.getRole_id().getUser_role());

				request.getSession().setAttribute("user", user);
				System.out.println("Login Controller: successfuly got user session for User: " + user);

				response.sendRedirect("ManagerHome.html");
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
			String loginErrorMessage = "Invalid Username & Password combination. Please try again.";
			request.setAttribute("loginErrorMessage", loginErrorMessage);
			response.sendRedirect("Login.html");
		} catch (Exception e) {
			request.setAttribute("authFailed", "try to login again");
			System.out.println("Login Controller: Auth Failed redirected to login.jsp");
			response.sendRedirect("Login.html");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
