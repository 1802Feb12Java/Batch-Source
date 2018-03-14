package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.util.FrontController;

/**
 * Servlet implementation class SessionServlet
 */
//@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SessionServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
    }
    

//	@Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
//        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//        resp.addHeader("Access-Control-Allow-Headers",
//                "Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
//        resp.addHeader("Access-Control-Allow-Credentials", "true");
//        resp.setContentType("application/json");
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
