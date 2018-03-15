package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.implement.EmployeeService;
import com.revature.implement.ManagerService;
import com.revature.objects.Employee;

public class ViewInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LoginServlet.class);
    public ViewInfo() {
        super();
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		resp.setContentType("text/HTML");
		EmployeeService Edao = new EmployeeService();
		Integer id = (Integer) session.getAttribute("id");
		String user = (String) session.getAttribute("username");
		Employee emp = Edao.viewInfo(id);
		resp.getWriter().write(emp.toString());
		log.info(user + " viewed their own information");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
