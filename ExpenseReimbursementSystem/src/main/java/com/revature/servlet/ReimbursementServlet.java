package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.bean.Reimbursement;
import com.revature.bean.helper.UpdateTicket;
import com.revature.bean.helper.ViewTicket;
import com.revature.services.ReimbursementServices;

import oracle.sql.TIMESTAMP;

public class ReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}
	
	//get/view Ticket information
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//TODO: get sessionID to see who is current user
//		HttpSession session = request.getSession(false);
//		int sessionRole = (int)(session.getAttribute("roleID"));
//		int sessionID = 1;
		try {
//			System.out.println("doPost received!");
			//grab json from put body
			BufferedReader reader = request.getReader();
			
			//gson then convert from json to vt
			Gson gson = new Gson();
			ViewTicket vt = gson.fromJson(reader, ViewTicket.class);
			String type = vt.getType();
			int author = vt.getAuthor();
			
			//create reimbursemet ticket services
			ReimbursementServices ts = new ReimbursementServices();
			//result list to write to response
			ArrayList<Reimbursement> rl = new ArrayList<Reimbursement>();
			
			if(type.equals("pending") || type.equals("approved") || 
			   type.equals("denied")) 
			{
				if(author == 0) {//view all
					rl = ts.getTicketsByStatus(type);
				}
				else {//specific employee
					rl = ts.getTicketsByAuthorAndStatus(author, type);
				}
			}
			else if(type.equals("image")) {//view image
				//????
			}
			else if(type.equals("all")) {
				if(author == 0)
					rl = ts.getAllTickets();
				else
					rl = ts.getTicketsByAuthor(author);
			}
			else {
				response.setStatus(400);
				System.out.println("Bad request");
			}
			
			//finished processing, write to response
			//setup response
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			//start of json response
			out.println("{");
			out.print("\"tickets\":[");
			for(int i = 0; i < rl.size(); i++) {
				Reimbursement r = rl.get(i);
				
				if(i==0) {
					out.println(gson.toJson(r));
				}
				else {
					out.println(",");
					out.println(gson.toJson(r));
				}
			}
			//end of json response
			out.println("]}");
			out.close();
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//update Ticket information
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try {
			//grab json from put body
			BufferedReader reader = request.getReader();
			//gson then convert from json to ut
			Gson gson = new Gson(); //Builder().setDateFormat("dd-MM-yyyy").create();
			
			UpdateTicket ut = gson.fromJson(reader, UpdateTicket.class);
			String type = ut.getType();
			
			ReimbursementServices ts = new ReimbursementServices();
			System.out.println("In PUT!");
			
			if(type.equals("submit")) { //call insert
				Reimbursement ticket = ut.getTicket();
				ticket.setSubmitted(Timestamp.valueOf( LocalDateTime.now()));
				ticket.setReceipt(null);
				ticket.setResolved(null);
				ticket.setResolver(0);
//				System.out.println(ticket.getRStatus());
				ticket.setRType(1);
				ticket.setRStatus(1);
//				System.out.println(ticket.toString());
//				ts.insertReimbursementTicket(ut.getTicket());
				ts.insertReimbursementTicket(ticket);
				System.out.println("Insert Ticket Completed!");
			}
			else if(type.equals("update")) {
				Reimbursement ticket = ut.getTicket();
				System.out.println("Approving Ticket");
				ticket.setResolved(Timestamp.valueOf( LocalDateTime.now()));
				ticket.setResolver(3);
				ticket.setRStatus(2);
				ticket.setReceipt(null);
				System.out.println(ticket.toString());
				ts.updateTicket(ticket);
				System.out.println("Update ticket complete");
			}
			
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//don't need this yet?
	}
}
