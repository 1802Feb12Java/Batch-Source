package com.revature.servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.revature.DAOs.ReimbursementDAOClass;
import com.revature.beans.Reimbursement;
import com.revature.factory.ConnectionFactory;

public class ApplicationServlet extends HttpServlet {
	private ConnectionFactory cf = ConnectionFactory.getInstance();
	private Connection conn = cf.getConnection();
	private ReimbursementDAOClass reDao = new ReimbursementDAOClass(conn);
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ApplicationServlet.class);

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers",
                "Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.setContentType("application/json");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		resp.setContentType("text/html");
		
		int authorID = (Integer) session.getAttribute("uid");
		double amount = new Double(req.getParameter("amount"));
		String description = new String(req.getParameter("description"));
		int type = new Integer(req.getParameter("type"));
		String receiptPath = "C:\\Users\\Trevor\\Documents\\workspace-sts\\Project1Test\\"+req.getParameter("receipt");
        
		ByteArrayOutputStream bos = null;
        try {
            File receiptFile = new File(receiptPath);
            FileInputStream fis = new FileInputStream(receiptFile);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
			fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String base64Blob = Base64.encodeBase64String(bos.toByteArray());
		
		Reimbursement reimbursementToInsert = new Reimbursement(amount, description, base64Blob, receiptPath, authorID, type, 1);
		
		try {
			reDao.createReimbursement(reimbursementToInsert);
			log.info("User "+authorID+" submitted a reimbursement request for $"+amount+" with description \""+description+"\"");
			resp.sendRedirect("http://localhost:4200/employeeHome");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
