package com.revature.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.bean.Reimbursement;

//CRUD operations
public interface ReimbursementDAO{
    //insert/create
    public void insertReimbursementTicket(Reimbursement r) throws ClassNotFoundException, SQLException; 
    //read/select from DB
    public ArrayList<Reimbursement> getAllTickets() throws ClassNotFoundException, SQLException; //all tickets
    public ArrayList<Reimbursement> getTicketsByAuthor(int uID) throws ClassNotFoundException,  SQLException; //from single employee
    public ArrayList<Reimbursement> getTicketsByAuthorAndStatus(int uID, String status) throws ClassNotFoundException,  SQLException; //employees viewing their own tix
    public ArrayList<Reimbursement> getTicketsByStatus(String status) throws ClassNotFoundException, SQLException;//approve/denying view all pendings
    //update Ticket
    public void updateTicket(Reimbursement r) throws ClassNotFoundException, SQLException;
    //delete Ticket
    public void deleteTiicket(Reimbursement r) throws ClassNotFoundException, SQLException;
}

