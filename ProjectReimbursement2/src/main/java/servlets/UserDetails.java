package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import DAO.DAO;
import objects.Reimbursement;
import objects.User;

/**
 * Servlet implementation class UserDetails
 */
@WebServlet("/UserDetails")
public class UserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ViewRiem.class);

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Servlet is working");
		
		try {

			User a = DAO.getUser("spiderman");
			JSONArray user_info = new JSONArray();
			JSONObject obj2 = new JSONObject();

			obj2.append("user_id", a.getUser_id());
			obj2.append("username", a.getUsername());
			obj2.append("password", "********");
			obj2.append("fname", a.getFirst_name());
			obj2.append("lname", a.getLast_name());
			obj2.append("email", a.getEmail());
	
			user_info.put(obj2);
			response.setContentType("application/json");
			response.getWriter().print(user_info.toString());
			logger.info(user_info);

			logger.info("Servlet is working");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
