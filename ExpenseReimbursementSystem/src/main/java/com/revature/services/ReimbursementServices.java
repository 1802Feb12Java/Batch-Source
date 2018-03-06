package com.revature.services;

import java.sql.SQLException;
import com.revature.bean.Reimbursement;
import com.revature.dao.ReimbursementDAO;

//CRUD operations
public class ReimbursementServices extends LookupServices implements ReimbursementDAO {
    //insert/create
    public void insertReimbursementTicket(Reimbursement r) throws SQLException {

    } 
    //read/select from DB
    //all tickets
    public void getAllTickets() throws SQLException {
        
    }
    //from single employee
    public void getTicketsByAuthor(int uID) throws SQLException {

    }
    //employees viewing their own tix
    public void getTicketsByAuthorAndStatus(int uID, String status) throws SQLException {

    }
    //approve/denying view all pendings
    public void getTicketsByStatus(String status) throws SQLException {

    }
    //update Ticket
    public void updateTicket(Reimbursement r) throws SQLException {

    }
    //delete Ticket
    public void deleteTiicket(Reimbursement r) throws SQLException {

    }
}

