package Project1.Projec1.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Project1.Projec1.UserDAO.UserDaoImple;
import Project1.Projec1.pojo.Users;
import Project1.Projec1.pojo.View;
import Project1.Projec1.requestdao.RequestDaoImpl;

@MultipartConfig
@WebServlet("/requestservlet")

public class RequestServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(RequestServlet.class);

	//get information from web to database
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
		
		RequestDaoImpl dao = new RequestDaoImpl();
		UserDaoImple daoUser = new UserDaoImple();

		try {
			logger.info("Post is working");
			Users user = (Users) req.getSession(false).getAttribute("user"); // get current user
			//resp.setContentType("application/octet-stream");
			PrintWriter out = resp.getWriter();
			logger.info(user.toString());
			
			String des = req.getParameter("description");
			String type = req.getParameter("type");
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        double amount = Double.parseDouble(req.getParameter("amount"));
			logger.info(amount);
	        logger.info(des);
	        logger.info(type);
	        logger.info(timestamp);
			// file upload
	        Part filePart = req.getPart("fileblob"); // Retrieves <input type="file" name="file">
	        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	        InputStream fileContent = filePart.getInputStream();
			user = daoUser.getOneUser(user.getU_username());//get latest information of the current user
			//create reimbursement request
			dao.createReimbursement(type, amount, des, fileContent, timestamp, user.getU_id());
			out.write("<div class=\"alert alert-success\" role=\"alert\">");
			out.write("Request submitted successfully");
			out.write("</div>");
			//out.write("alert(Request created)");
			req.getRequestDispatcher("reimbursementRequest.html").include(req, resp);  

		//	resp.sendRedirect("reimbursementRequest.html");
			

		} catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NumberFormatException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	//pull information and convert to json and pass to xhr
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		Users user = new Users();
		user = (Users) req.getSession(false).getAttribute("user"); // get current user
		ObjectMapper json = new ObjectMapper();
		RequestDaoImpl requestDao = new RequestDaoImpl();
		UserDaoImple dao = new UserDaoImple();
		if(user != null) {
			try {
				user = dao.returnOneUser(user.getU_id());//get new data
				ArrayList<View> viewList = requestDao.getRequestView(user.getU_id());
				//convert object to json string
				String jsonInString = json.writeValueAsString(viewList);
				//convert object to json string and pretty print
			//	jsonInString = json.writerWithDefaultPrettyPrinter().writeValueAsString(viewList);
				//logger.infor(jsonInString);
				logger.info(jsonInString);
				out.write(jsonInString);
				
				
			}catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	}
}
