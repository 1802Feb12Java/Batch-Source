package Project1.Projec1.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet{

	@Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
              throws ServletException, IOException {  

		resp.setContentType("text/html");  
		PrintWriter out = resp.getWriter();  
        //req.getRequestDispatcher("login.html").include(req, resp);  

		//req.getRequestDispatcher("login.html").include(req, resp);  
  
		HttpSession session = req.getSession(false);  
		if(session!=null){  
			String name = (String)session.getAttribute("username");  
 
			//out.print("Hello, "+name+"  to Profile");  
		}  
		else{  
			out.print("Please login first");  
			req.getRequestDispatcher("login.html").include(req, resp);  
		}  
		out.close();  
	}  
}
