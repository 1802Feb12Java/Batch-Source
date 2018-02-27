package JDBCBank.JDBCBank.employee.bean;

import java.util.ArrayList;


public interface EmployeeDao {
	public ArrayList<Employee> getAllEmployee();
	public Employee getEmployee(String username);
	public void insertEmployeeQuery(String user, String pass, String fname, String lname);
	public void updateCustomerUser(String user,int id);
	public void updateCustomerPass(String pass, int id);
	public void updateCustomerLname(String lname ,int id);
	public void updateCustomerFname(String fname, int id);
	public void updateCustomerAddr(String addr,int id);
	public void updateCustomerPhone(String phone,int id);





	

}
