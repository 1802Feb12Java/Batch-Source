package webfunctions;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.User;
import pov.ReimFunc;

public class LoginController {
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("Reached: " + username + password);
		
		ReimFunc delegate = new ReimFunc();
		User user;
		
		HttpSession session = null;
		try{
			user = delegate.login(username, password);
			
			System.out.println("Reached start of Login Controller for user: " +user.getFirst_name());
					
			if(user != null && user.getRole_id().getId()==2) {
				System.out.println("Login controller redirect for type: " + user.getRole_id().getUser_role());
				response.sendRedirect("EmpHome.jsp");
				session=request.getSession();			
			}else if(user != null && user.getRole_id().getId()==1) {
				System.out.println("Login controller redirect for type: " + user.getRole_id().getUser_role());
				response.sendRedirect("ManagerHome.jsp");
				session=request.getSession();		
			}
			
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("user", user);
			System.out.println("Login Controller: successfuly got user session for User: " + user);
		}catch(AuthenticationException e) {
			e.printStackTrace();
			String loginErrorMessage = "Invalid Username & Password combination. Please try again.";
			request.setAttribute("loginErrorMessage", loginErrorMessage); 
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("authFailed", "try to login again");
			System.out.println("Login Controller: Auth Failed redirected to login.jsp");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			e.printStackTrace();
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// invalidate user session, redirect to login page
		session.invalidate();
		System.out.println("User successfully logged out.");
		response.sendRedirect("/webapp/login.jsp");
	}
}