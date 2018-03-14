package com.revature.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.revature.bean.User;

/**
 * Servlet implementation class UserHomeServlet
 */
public class UserHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext ctx = getServletContext();
		File webinfPath = new File(ctx.getRealPath("WEB-INF/"));
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			// No user logged in -> goto login page.
			response.sendRedirect("login");
			return;
		}
		
		
		User user = (User)(session.getAttribute("loggeduser"));
		response.setContentType("text/html");
		if(user.getRole().getRole().equalsIgnoreCase("manager")) {
			Document htmlPage = Jsoup.parse(new File(webinfPath, "manager-base.html"), null);
			Document htmlFragment = Jsoup.parse(new File(webinfPath, "manager-home.fragment.html"), null);
			htmlPage.getElementById("user").empty().appendText(user.getUsername());
			
			Elements fragment = htmlFragment.getElementById("fragment").children();
			
			htmlPage.getElementById("content").append(fragment.outerHtml());
			htmlPage.body().appendElement("script").attr("src", "resources/logout.js");
			
			response.getWriter().print(htmlPage.outerHtml());
			
		} else if(user.getRole().getRole().equalsIgnoreCase("employee")) {
			Document htmlPage = Jsoup.parse(new File(webinfPath, "employee-base.html"), null);
			Document htmlFragment = Jsoup.parse(new File(webinfPath, "employee-home.fragment.html"), null);
			
			Elements fragment = htmlFragment.getElementById("fragment").children();
			
			htmlPage.getElementById("content").append(fragment.outerHtml());
			htmlPage.body().appendElement("script").attr("src", "resources/logout.js");
			
			response.getWriter().print(htmlPage.outerHtml());
			
		} else {
			response.sendRedirect("logout");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
