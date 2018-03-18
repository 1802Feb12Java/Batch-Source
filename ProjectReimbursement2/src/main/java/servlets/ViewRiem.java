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
import DAO.UserDAO;
import objects.Reimbursement;
import objects.User;

/**
 * Servlet implementation class ViewRiem
 */
@WebServlet("/ViewRiem")
public class ViewRiem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ViewRiem.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewRiem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Servlet is working");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		try {

			List<Reimbursement> list = DAO.getAllReimbs();
			JSONArray reimb = new JSONArray();

			for (int i = 0; i < list.size(); i++) {

				JSONObject obj = new JSONObject();
				Reimbursement r = list.get(i);
				System.out.println("R and contents: "+r);
				obj.append("request_type", "Food");//r.getType_id().getType());
				obj.append("description", r.getDescription());
				obj.append("amount", r.getAmount());
				obj.append("status", "Pending");//r.getStatus_id().status);
				obj.append("request_id", r.getId());
				obj.append("date_submitted", ts);
				obj.append("date_resolved", r.getDate_resolved());
				obj.append("request_resolver", "Bob");//r.getResolver_id().getFullName());

				reimb.put(obj);
			}

			response.setContentType("application/json");
			response.getWriter().print(reimb.toString());
			logger.info(reimb);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
