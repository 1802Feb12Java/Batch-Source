package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.util.FrontController;

public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SessionServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		FrontController.addHeader(resp);
		
		if(session!=null){
			resp.setContentType("application/json");
			resp.getWriter().write("{\"uid\":\""+session.getAttribute("uid")+"\",");
			resp.getWriter().write("\"role\":\""+session.getAttribute("role")+"\"}");
		} else {
			resp.setContentType("application/json");
			resp.getWriter().write("{\"uid\":null}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
