package com.talanian.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanian.dao.SystemDAO;
import com.talanian.dao.SystemDAOImpl;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;


public class Submit extends HttpServlet{
	private static final long serialVersionUID = 1L;
	SystemDAO dao = new SystemDAOImpl();


    private boolean isMultipart;
    private String filePath = "C:\\temp\\";
    private File file ;
    String fileName;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


        PrintWriter out = response.getWriter();

        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");



        DiskFileItemFactory factory = new DiskFileItemFactory();

        int maxMemSize = 10 * 1024;
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(new File("c:\\temp"));

        ServletFileUpload upload = new ServletFileUpload(factory);
        int maxFileSize = (1920*1080*3)*2;
        upload.setSizeMax(maxFileSize);

        try {
            List fileItems = upload.parseRequest(request);

            int userID=0;
            double amount=0.0;
            String desc="";

            for (Object fileItem : fileItems) {
                FileItem fi = (FileItem) fileItem;
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    fileName = fi.getName();


                    file = new File(filePath+fileName);

                    fi.write( file ) ;
                }
                else
                {
                    if(((FileItem) fileItem).getFieldName().equals("id2")) userID = Integer.parseInt(((FileItem) fileItem).getString());
                    if(((FileItem) fileItem).getFieldName().equals("amount")) amount = Double.parseDouble(((FileItem) fileItem).getString());
                    if(((FileItem) fileItem).getFieldName().equals("reimbdesc")) desc = ((FileItem) fileItem).getString();

                }
            }

            String[] filenameList = fileName.split(Pattern.quote("."));
            out.write("<body onload='myFunction()'><a href='#' onclick='myFunction()'>Redirecting...Click here to continue if nothing happens</a></body>\n<script>function myFunction(){\n");
            boolean submitted = dao.submitFields(userID, amount, desc, file, filenameList[filenameList.length-1]);
            if(submitted) {
                List<String> reimbursement = dao.returnRList(userID, false);
                if(reimbursement == null || reimbursement.isEmpty()) {
                    System.out.println("result empty");
                    out.write("alert('Submission Failed');\n"); //dummy code for a blank table.
                } else {
                    out.write("\nsessionStorage.table = \"");
                    for(String temp : reimbursement) {
                        out.write(temp);
                    }
                    out.write("\";\n");
                }
            }
            out.write("window.location.href = 'Employee.html';}</script>");

        } catch(Exception ex) {
            ex.printStackTrace();
        }

	}
}