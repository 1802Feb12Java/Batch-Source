package servlets;

import java.io.IOException;
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

import connect.DAO;
import objects.Reimbursement;

/**
 * Servlet implementation class ViewRiemMan
 */
@WebServlet("/ViewRiemMan")
public class ViewRiemMan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ViewRiem.class);
   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRiemMan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		logger.info("Servlet is working");
		try {

			List<Reimbursement> list = DAO.getAllReimbs();
			System.out.println("*****************List contains "+list);
			JSONArray reimb = new JSONArray();

			for (int i = 0; i < list.size(); i++) {

				JSONObject obj = new JSONObject();
				Reimbursement r = list.get(i);
				
				obj.append("first_name", r.getAuthor_id().getFirst_name());
				obj.append("last_name", r.getAuthor_id().getLast_name());
				obj.append("request_type", "Food");//r.getType_id().getType());
				obj.append("description", r.getDescription());
				obj.append("amount", r.getAmount());
				obj.append("status", "Pending");
				obj.append("request_id", r.getId());

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
