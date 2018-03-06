package com.revature.services;

import java.sql.*;
import com.revature.util.ConnFactory;

/* LookupServices
 * Helper abstract class to do look ups
 *      look up user roles, id <-> string
 *      look up ticket type id <-> string
 *      look up ticket status id <-> string
 *      get u_id and r_id sequences
 * Extended by the other two Services so they can both use it
 */
abstract class LookupServices{
    public String lookUpRole(int roleID) throws SQLException {

        //get connection from connFactory
        Connection conn = ConnFactory.getInstance().getConnection();

        //setup prepared statements
        PreparedStatement lookupPrepared = null;
        String lookupString = "SELECT ur_role FROM ers_user_roles WHERE ur_id=?";

        lookupPrepared = conn.prepareStatement(lookupString);
        lookupPrepared.setInt(1,roleID);
        ResultSet rs = lookupPrepared.executeQuery();
        //closing connection
        conn.close();
        if(rs.next()){
            return rs.getString(2);
        }
        
        return null;
    }
}