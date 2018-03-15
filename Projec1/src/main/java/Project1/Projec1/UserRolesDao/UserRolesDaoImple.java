package Project1.Projec1.UserRolesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Project1.Projec1.UserDAO.UserDaoImple;
import Project1.Projec1.oraclejdbc.ConnFactory;
import Project1.Projec1.pojo.UserRoles;
import Project1.Projec1.pojo.Users;

public class UserRolesDaoImple implements UserRoleDao{

	private Logger logger = Logger.getLogger(UserRolesDaoImple.class);

	public ArrayList<UserRoles> getAllUserRole() throws SQLException {
		ArrayList<UserRoles> list = new ArrayList();
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		String sql = "Select * from ers_user_roles";

		
		PreparedStatement ps =  con.prepareStatement(sql); //prepared statement
		ResultSet rs = ps.executeQuery();//pull information
		
		while (rs.next()) {
			logger.info("retrieving object");
			UserRoles ur = new UserRoles();
			ur.setUr_id(rs.getInt(1));
			ur.setUr_role(rs.getString(2));
			
			list.add(ur);// save data in arraylist

			
		}
		//close connection
		ps.close();
		rs.close();
		con.close();
				
		return list;
	}

	public UserRoles getOneUserRole(int rid) throws SQLException {
		ArrayList<UserRoles> list = getAllUserRole();
		
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getUr_id() == rid){
				return list.get(i);
			}
		}
		return null;
	}

	public void createUserRole(String role) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public boolean checkAdmin(int rid) throws SQLException {
		ArrayList<UserRoles> list = getAllUserRole();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getUr_id() == rid) {
				if(list.get(i).getUr_role().equals("admin") ) {
					return true;
				}
			}
		}
		return false;
	}

}
