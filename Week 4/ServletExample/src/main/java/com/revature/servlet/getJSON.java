package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class getJSON
 */
public class getJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getJSON() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person matt = new Person("Josh is stupid");
		
		ObjectMapper mapper = new ObjectMapper();
		String mattJSON = mapper.writeValueAsString(matt);
		System.out.println(mattJSON);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(mattJSON);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(request.getParameter("person"));
		Person person = mapper.readValue(request.getParameter("person"), Person.class);
		System.out.println(person);
	}

}
