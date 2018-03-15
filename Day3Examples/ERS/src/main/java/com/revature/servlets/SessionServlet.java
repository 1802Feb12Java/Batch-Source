package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SessionServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!=null){
			response.setContentType("application/json");
			response.getWriter().write("{\"username\":\""+session.getAttribute("username")+"\"}");
			response.getWriter().write("{\"u_id\":\""+session.getAttribute("u_id")+"\"}");
			response.getWriter().write("{\"sel_uid\":\""+session.getAttribute("sel_uid")+"\"}");
		} else {
			response.setContentType("application/json");
			response.getWriter().write("{\"username\":null}");
			response.getWriter().write("{\"u_id\":null}");
			response.getWriter().write("{\"sel_uid\":null}");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
