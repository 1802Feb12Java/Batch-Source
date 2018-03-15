package Project1.Projec1.UserRolesDao;

import java.sql.SQLException;
import java.util.ArrayList;

import Project1.Projec1.pojo.UserRoles;

public interface UserRoleDao {
	public ArrayList<UserRoles> getAllUserRole() throws SQLException;
	public UserRoles getOneUserRole (int rid) throws SQLException;
	public void createUserRole (String role) throws SQLException;
	public boolean checkAdmin (int rid) throws SQLException;
}
