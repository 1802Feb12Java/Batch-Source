package com.revature.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class UploadReceiptServlet extends HttpServlet{

	@Override 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		Part filePart = request.getPart("img");
		int reimNum = Integer.parseInt(request.getParameter("reimNum"));
		//String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	    InputStream fileContent = filePart.getInputStream();
	    
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    
	    int nRead;
	    byte[] data = new byte[16384];

	    while ((nRead = fileContent.read(data, 0, data.length)) != -1) {
	      buffer.write(data, 0, nRead);
	    }

	    buffer.flush();

	    byte[] bytes = buffer.toByteArray();
	}
}
