package com.revature.dao;

import java.sql.SQLException;
import com.revature.bean.Reimbursement;

//CRUD operations
public interface ReimbursementDAO{
    //insert/create
    public void insertReimbursementTicket(Reimbursement r) throws SQLException; 
    //read/select from DB
    public void getAllTickets() throws SQLException; //all tickets
    public void getTicketsByAuthor(int uID) throws SQLException; //from single employee
    public void getTicketsByAuthorAndStatus(int uID, String status) throws SQLException; //employees viewing their own tix
    public void getTicketsByStatus(String status) throws SQLException;//approve/denying view all pendings
    //update Ticket
    public void updateTicket(Reimbursement r) throws SQLException;
    //delete Ticket
    public void deleteTiicket(Reimbursement r) throws SQLException;
}

