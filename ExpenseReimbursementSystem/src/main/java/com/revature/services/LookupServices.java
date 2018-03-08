package com.revature.services;

import java.sql.*;
import com.revature.util.ConnManager;

/* LookupServices
 * Helper abstract class to do look ups
 *      look up user roles, id <-> string
 *      look up ticket type id <-> string
 *      look up ticket status id <-> string
 * Extended by the other two Services so they can both use it
 * 
 * TODO: check the inserts, specify the columns since generate id is a thing
 */
abstract class LookupServices{
	
	private Connection conn;
	
	/* 
	 * public String lookUpRole(int roleID)
	 * Does a look up for roles from Role ID
	 * takes in an int roleID to lookup
	 * returns the String of the role
	*/
    public String lookUpRole(int roleID) throws ClassNotFoundException, SQLException {

        //get connection from ConnManager
    	this.conn = ConnManager.getInstance().getConnection();

        //setup prepared statements
        PreparedStatement lookupPrepared = null;
        String lookupString = "SELECT ur_role FROM ers_user_roles WHERE ur_id=?";

        lookupPrepared = this.conn.prepareStatement(lookupString);
        lookupPrepared.setInt(1,roleID);
        ResultSet rs = lookupPrepared.executeQuery();
        //closing connection
        conn.close();
        if(rs.next()){
            return rs.getString(1);
        }
        
        return null;
    }
    
    public int lookupRoleID(String role) throws ClassNotFoundException, SQLException {
    	//get connection from ConnManager
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	PreparedStatement lookupPrepared = null;
    	String lookupString = "SELECT ur_id FROM ers_user_roles WHERE ur_roles=?";
    	
    	lookupPrepared = this.conn.prepareStatement(lookupString);
    	lookupPrepared.setString(1, role);
    	//execute select
    	ResultSet rs = lookupPrepared.executeQuery();
    	//close connection
    	conn.close();
    	//returns
    	if(rs.next())
    		return rs.getInt(1);
    	return 0;
    }
    
    /*
     * public String lookUpRType(rtID)
     * Does a look up for Reimbursement Type from rtype id
     * return the String of the type
     * null otherwise
    */
    public String lookUpRType(int rt_id) throws ClassNotFoundException, SQLException {
    	//get connection from ConnManager
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	//setup prepared statement
    	PreparedStatement lookupPrepared = null;
    	String lookupString = "SELECT rt_type FROM ers_reimbursement_type WHERE rt_id=?";
    	
    	lookupPrepared = this.conn.prepareStatement(lookupString);
    	lookupPrepared.setInt(1, rt_id);
    	//execute select query
    	ResultSet rs = lookupPrepared.executeQuery();
    	//close connection
    	conn.close();
    	if(rs.next()) {
    		return rs.getString(1);
    	}
    	return null;
    }
    
    /*
     * public String lookupRStatus(int rs_id)
     * Does a look up for Reimbursement Status from rstatus id
     * return the string of the type
     * null otherwise
     */
    public String lookupRStatus(int rs_id) throws ClassNotFoundException, SQLException {
    	//get connection from ConnManager
    	this.conn = ConnManager.getInstance().getConnection();
    	//setup prepared statements
    	PreparedStatement lookupPrepared = null;
    	String lookupString = "SELECT rs_status FROM ers_reimbursement_status WHERE rs_id=?";
    	lookupPrepared = this.conn.prepareStatement(lookupString);
    	lookupPrepared.setInt(1, rs_id);
    	//execute select query
    	ResultSet rs = lookupPrepared.executeQuery();
    	//close connection
    	conn.close();
    	if(rs.next()) {
    		return rs.getString(1);
    	}
    	return null;
    }
     
    
}