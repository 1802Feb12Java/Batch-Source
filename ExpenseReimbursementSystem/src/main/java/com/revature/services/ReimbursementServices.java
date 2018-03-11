package com.revature.services;

import java.sql.*;
import java.util.ArrayList;
import com.revature.bean.Reimbursement;
import com.revature.dao.ReimbursementDAO;
import com.revature.util.ConnManager;

//CRUD operations
public class ReimbursementServices extends LookupServices implements ReimbursementDAO {
	//private connection for this Service class
	private Connection conn;
	
	//insert/create
    public void insertReimbursementTicket(Reimbursement r) throws ClassNotFoundException, SQLException {
    	//get connection
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	//use stored procedure
    	CallableStatement callInsert= null;
    	String insertString = "{CALL insert_ticket(?,?,?,?,?,?,?,?,?)";
    	callInsert = conn.prepareCall(insertString);
    	
    	//set up parameters
    	callInsert.setDouble(1, r.getAmount());
    	callInsert.setString(2, r.getDescription());
    	callInsert.setBlob(3, r.getReceipt());
    	callInsert.setDate(4, r.getSubmitted());
    	callInsert.setDate(5, r.getResolved());
    	callInsert.setInt(6, r.getAuthor().getUserID());
    	callInsert.setInt(7, r.getResolver().getUserID());
    	callInsert.setInt(8, this.lookupRTypeID(r.getRType()));
    	callInsert.setInt(9, this.lookupRStatusID(r.getRStatus()));
    	
    	//execute delete
    	callInsert.executeUpdate();
    } 
    
    //read/select from DB
    //all tickets
    public ArrayList<Reimbursement> getAllTickets() throws ClassNotFoundException, SQLException {
        //get connection
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	//set up prepared statements
    	PreparedStatement selectPrepared = null;
    	String selectStatement = "SELECT * FROM ers_reimbursements";
    	
    	selectPrepared = conn.prepareStatement(selectStatement);
    	ResultSet rs = selectPrepared.executeQuery();
    	
    	ArrayList<Reimbursement> allTickets = new ArrayList<Reimbursement>();
    	while(rs.next()) {
    		allTickets.add(this.rsToTicket(rs));
    	}
    	return allTickets;
    	
    }
    //from single employee
    public ArrayList<Reimbursement> getTicketsByAuthor(int uID) throws ClassNotFoundException, SQLException {
    	//get connection
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	//setup select
    	PreparedStatement selectPrepared = null;
    	String selectString = "SELECT * FROM ers_reimbursements WHERE u_author_id=?";
    	
    	selectPrepared = conn.prepareStatement(selectString);
    	selectPrepared.setInt(1, uID);
    	ResultSet rs = selectPrepared.executeQuery();
    	
    	ArrayList<Reimbursement> ticketsByAuthor = new ArrayList<Reimbursement>();
    	while(rs.next()){
    		ticketsByAuthor.add(rsToTicket(rs));
    	}
    	return ticketsByAuthor;
    }
    //employees viewing their own tickets
    public ArrayList<Reimbursement> getTicketsByAuthorAndStatus(int uID, String status) throws ClassNotFoundException, SQLException {
    	//get connection
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	//setup select
    	PreparedStatement selectPrepared = null;
    	String selectString = "SELECT * FROM ers_reimbursements WHERE u_author_id=? AND rt_status=?";
    	
    	selectPrepared = conn.prepareStatement(selectString);
    	selectPrepared.setInt(1, uID);
    	selectPrepared.setInt(2, this.lookupRStatusID(status));
    	ResultSet rs = selectPrepared.executeQuery();
    	
    	ArrayList<Reimbursement> ticketsByAuthAndStat = new ArrayList<Reimbursement>();
    	while(rs.next()){
    		ticketsByAuthAndStat.add(rsToTicket(rs));
    	}
    	return ticketsByAuthAndStat;
    }
    //approve/denying view all pending
    public ArrayList<Reimbursement> getTicketsByStatus(String status) throws ClassNotFoundException, SQLException {
    	//get connection
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	//setup select
    	PreparedStatement selectPrepared = null;
    	String selectString = "SELECT * FROM ers_reimbursements WHERE rt_status=?";
    	
    	selectPrepared = conn.prepareStatement(selectString);
    	selectPrepared.setInt(1, this.lookupRStatusID(status));
    	ResultSet rs = selectPrepared.executeQuery();
    	
    	ArrayList<Reimbursement> ticketsByStat = new ArrayList<Reimbursement>();
    	while(rs.next()){
    		ticketsByStat.add(rsToTicket(rs));
    	}
    	return ticketsByStat;
    }
    //update Ticket
    public void updateTicket(Reimbursement r) throws ClassNotFoundException, SQLException {
    	//use callable statement
    	//get connection
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	//use stored procedure
    	CallableStatement callUpdate= null;
    	String updateString = "{CALL update_ticket(?,?,?,?,?,?,?,?,?,?)";
    	callUpdate= conn.prepareCall(updateString);
    	
    	//set up parameters
    	callUpdate.setInt(1,r.getRID());
    	callUpdate.setDouble(2,r.getAmount());
    	callUpdate.setString(3,r.getDescription());
    	callUpdate.setBlob(4,r.getReceipt());
    	callUpdate.setDate(5,r.getSubmitted());
    	callUpdate.setDate(6,r.getResolved());
    	callUpdate.setInt(7,r.getAuthor().getUserID());
    	callUpdate.setInt(8,r.getResolver().getUserID());
    	callUpdate.setInt(9,this.lookupRTypeID(r.getRType()));
    	callUpdate.setInt(10,this.lookupRStatusID(r.getRStatus()));
    	
    	//execute
    	callUpdate.executeUpdate();
    	
    }
    //delete Ticket
    public void deleteTiicket(Reimbursement r) throws ClassNotFoundException, SQLException {
    	//use stored procedure
    	CallableStatement callDelete = null;
    	String deleteString = "{CALL delete_ticket(?)";
    	callDelete = conn.prepareCall(deleteString);
    	
    	//set up fields
    	callDelete.setInt(1, r.getRID());
    	
    	//execute delete
    	callDelete.executeUpdate();
    }
    
    Reimbursement rsToTicket(ResultSet rs) throws ClassNotFoundException, SQLException {
    	Reimbursement ticket = new Reimbursement();
    	
    	ticket.setRID(rs.getInt(1));
    	ticket.setAmount(rs.getDouble(2));
    	ticket.setDescription(rs.getString(3));
    	ticket.setReceipt(rs.getBlob(4));
    	ticket.setSubmitted(rs.getDate(5));
    	ticket.setResolved(rs.getDate(6));
    	UserServices us = new UserServices();
    	ticket.setAuthor(us.getUserByID(rs.getInt(7)));
    	ticket.setResolver(us.getUserByID(rs.getInt(8)));
    	ticket.setRType(this.lookupRType(rs.getInt(9)));
    	ticket.setRStatus(this.lookupRStatus(rs.getInt(10)));
    	
    	return ticket;
    	
    }
    
}

