package com.revature.services;

import java.sql.*;
import com.revature.util.ConnManager;
import com.revature.bean.User;

/* LookupServices
 * Helper abstract class to do look ups
 *      look up user roles, id <-> string
 *      look up ticket type id <-> string
 *      look up ticket status id <-> string
 * Extended by the other two Services so they can both use it
 */
abstract class LookupServices{
	
	private Connection conn;
	
	/* 
	 * String lookUpRole(int roleID)
	 * Does a look up for roles from Role ID
	 * takes in an int roleID to lookup
	 * returns the String of the role
	*/
    String lookupRole(int roleID) throws ClassNotFoundException, SQLException {

        //get connection from ConnManager
    	this.conn = ConnManager.getInstance().getConnection();

        //setup prepared statements
        PreparedStatement lookupPrepared = null;
        String lookupString = "SELECT ur_roles FROM ers_user_roles WHERE ur_id=?";

        lookupPrepared = this.conn.prepareStatement(lookupString);
        lookupPrepared.setInt(1,roleID);
        ResultSet rs = lookupPrepared.executeQuery();

        if(rs.next()){
            return rs.getString(1);
        }
        
        return null;
    }
    
    /*
     * int lookupRoleID(String role)
     * Does a look up for role ID given a role string
     * returns the integer of the id
     * return 0 if not found
    */
    int lookupRoleID(String role) throws ClassNotFoundException, SQLException {
    	//get connection from ConnManager
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	PreparedStatement lookupPrepared = null;
    	String lookupString = "SELECT ur_id FROM ers_user_roles WHERE ur_roles=?";
    	
    	lookupPrepared = this.conn.prepareStatement(lookupString);
    	lookupPrepared.setString(1, role);
    	//execute select
    	ResultSet rs = lookupPrepared.executeQuery();

    	//returns
    	if(rs.next()) {
    		return rs.getInt(1);
    	}
    	
    	return 0;
    }
    
    /*
     * public String lookUpRType(rtID)
     * Does a look up for Reimbursement Type from rtype id
     * return the String of the type
     * null otherwise
    */
    String lookupRType(int rt_id) throws ClassNotFoundException, SQLException {
    	//get connection from ConnManager
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	//setup prepared statement
    	PreparedStatement lookupPrepared = null;
    	String lookupString = "SELECT rt_type FROM ers_reimbursement_type WHERE rt_id=?";
    	
    	lookupPrepared = this.conn.prepareStatement(lookupString);
    	lookupPrepared.setInt(1, rt_id);
    	//execute select query
    	ResultSet rs = lookupPrepared.executeQuery();

    	if(rs.next()) {
    		return rs.getString(1);
    	}
    	return null;
    }
    
    /*
     * int lookupRTypeID(String rt_type)
     * Does a look up for reimbursement type ID given a rtype string
     * returns the integer of the id
     * return 0 if not found
    */
    int lookupRTypeID(String rt_type) throws ClassNotFoundException, SQLException{
    	//get connection from connManager
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	PreparedStatement lookupPrepared = null;
    	String lookupString = "SELECT rt_id FROM ers_reimbursement_type WHERE rt_type=?";
    	
    	
    	lookupPrepared = conn.prepareStatement(lookupString);
    	lookupPrepared.setString(1, rt_type);
    	//execute  select
    	ResultSet rs = lookupPrepared.executeQuery();
    	if (rs.next()) {
    		return rs.getInt(1);
    	}
    	
    	return 0;
    }
    
    /*
     * public String lookupRStatus(int rs_id)
     * Does a look up for Reimbursement Status from rstatus id
     * return the string of the type
     * null otherwise
     */
    String lookupRStatus(int rs_id) throws ClassNotFoundException, SQLException {
    	//get connection from ConnManager
    	this.conn = ConnManager.getInstance().getConnection();
    	//setup prepared statements
    	PreparedStatement lookupPrepared = null;
    	String lookupString = "SELECT rs_status FROM ers_reimbursement_status WHERE rs_id=?";
    	lookupPrepared = this.conn.prepareStatement(lookupString);
    	lookupPrepared.setInt(1, rs_id);
    	//execute select query
    	ResultSet rs = lookupPrepared.executeQuery();

    	if(rs.next()) {
    		return rs.getString(1);
    	}
    	return null;
    }

    /*
     * int lookupRStatusID(String rs_status)
     * Does a look up for reimbursement type ID given a rtype string
     * returns the integer of the id
     * return 0 if not found
    */
    int lookupRStatusID(String rs_status) throws ClassNotFoundException, SQLException{
    	//get connection from connmanager
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	PreparedStatement lookupPrepared = null;
    	String lookupString = "SELECT rs_id FROM ers_reimbursement_status WHERE rs_status=?";
    	
    	
    	lookupPrepared = conn.prepareStatement(lookupString);
    	lookupPrepared.setString(1, rs_status);
    	//execute  select
    	ResultSet rs = lookupPrepared.executeQuery();

    	if(rs.next()) {
    		return rs.getInt(1);
    	}
    	
    	return 0;
    }
    
    
    /*
     * Helper function to convert a row of ResultSet to a user
     */
    User rsToUser(ResultSet rs) throws ClassNotFoundException, SQLException {

    	User u = new User();
    	u.setUserID(rs.getInt(1));
    	u.setUsername(rs.getString(2));
    	u.setPassword(rs.getString(3));
    	u.setFirstName(rs.getString(4));
    	u.setLastName(rs.getString(5));
    	u.setEmail(rs.getString(6));
    	u.setRole(this.lookupRole(rs.getInt(7)));
    	
    	return u;
    }
    
}
