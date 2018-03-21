package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * init 	- Initializes the servlet in the servlet container (web container)
 * 			- Called once
 * 				- Either on first request OR on startup (if <load-on-startup> tag is present in <servlet> tag)
 * 			- Want to test it?
 * 				- Republish the server
 *  
 * service 	- Handles a request to this servlet. Basically forwards the request to the appropriate method (doGet, doPost, doPut, doDelete, doHead, doOptions, doTrace)
 * 				- NOTE: this method is abstract in GenericServlet
 * 				- NOTE: this method is implemented in HttpServlet
 * 			- Called on every request to this servlet
 *			- Want to test it?
 * 		   		- http://localhost:7001/ServletExamples/lmUrl 
 * 
 * destroy 	- Destroys the servlet
 * 			- Typically called when servlet container stops/restarts
 * 		   		- Most likely not called during normal operations
 * 				- So maybe called once
 * 			- Want to test it?
 * 				- Republish the server
 */
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifecycleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.err.println("init");
		super.init();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.err.println("destroy");
		super.destroy();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.err.println("service");
		super.service(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		System.out.println("doGet");
		
	}

}
