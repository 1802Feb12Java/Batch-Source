package com.revature.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.revature.bean.User;
import com.revature.dao.UserDao;

/**
 * Servlet implementation class AccountListServlet
 */
public class AccountListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext ctx = getServletContext();
		File webinfDir = new File(ctx.getRealPath("WEB-INF"));
		HttpSession session = request.getSession(false);
		Document basePage, fragmentPage, recordFragmentPage;
		User user;
		
		if(session == null) {
			// No valid session -> login page.
			response.sendRedirect("login");
			return;
		}
		
		user = (User)(session.getAttribute("loggeduser"));
		
		// Construct page
		if(user.getRole().getRole().equalsIgnoreCase("manager")) {
			// Get page components.
			basePage = Jsoup.parse(new File(webinfDir, "manager-base.html"), null);
			fragmentPage = Jsoup.parse(new File(webinfDir, "account-list.fragment.html"), null);
			recordFragmentPage = Jsoup.parse(new File(webinfDir, "account-record.fragment.html"), null);
			basePage.getElementById("user").empty().appendText(user.getUsername());
			
			// Add table styling sheet
			basePage.head().appendElement("link").attr("rel", "stylesheet")
												 .attr("href", "resources/tables.css");
			
			// Combine components.
			Elements fragment = fragmentPage.getElementById("fragment").children();
			basePage.getElementById("content").append(fragment.outerHtml());
			
			
			// Fill out the table.
			Elements recordFragment = recordFragmentPage.getElementById("fragment").children();
			try {
				UserDao userDao = new UserDao();
				List<User> userList = userDao.getAllUsers();
				userList.sort((a, b) -> {
					int compareResult = a.getLastName().compareTo(b.getLastName());
					
					if(compareResult != 0) {
						return compareResult;
					}
					
					compareResult = a.getFirstName().compareTo(b.getFirstName());
					
					if(compareResult != 0) {
						return compareResult;
					}
					
					compareResult = a.getUsername().compareTo(b.getUsername());
					
					return compareResult;
				});
				Element recordsTableBody = basePage.getElementById("account-records");
				userList.forEach((User u) -> {
					// Clone and set fields.
					Elements recordFragCopy = recordFragment.clone();
					Element e = recordFragCopy.get(0); // record
					e.getElementsByClass("lastname-cell").get(0).text(u.getLastName());
					e.getElementsByClass("firstname-cell").get(0).text(u.getFirstName());
					e.getElementsByClass("username-cell").get(0).text(u.getUsername());
					e.getElementsByClass("email-cell").get(0).text(u.getEmail());
					e.getElementsByClass("role-cell").get(0).text(u.getRole().getRole());
					
					recordsTableBody.appendChild(e);
				});
				
				
			} catch(SQLException | IOException ex) {}
			
			response.getWriter().println(basePage.outerHtml());
		} else {
			// Unauthorized role -> logout.
			response.sendRedirect("logout");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); // forward to GET
		
		
		
	}

}
