package JDBCBank.JDBCBank.employee.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import JDBCBank.JDBCBank.BankDriver;
import JDBCBank.JDBCBank.oraclejdbc.ConnFactory;

public class EmployeeDaoImpl implements EmployeeDao {
	   static Logger log = Logger.getLogger(EmployeeDaoImpl.class);

	//create arrayList of employee
	ArrayList<Employee> empList = new ArrayList();
	public EmployeeDaoImpl()//default constructor
	{
		
	}
	public EmployeeDaoImpl(Employee e) {
		empList.add(e);
	}
	public void insertEmployeeQuery(String user, String pass, String fname, String lname)
	{
		log.info("this is in insertEmployeeQuery method");
		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//prepare an insert statement into customer database
		PreparedStatement insertStatement = connect.prepareStatement("insert into admin values (?,?,?,?,?)");
		insertStatement.setInt(1, 1);
		insertStatement.setString(2, user);
		insertStatement.setString(3, pass);
		insertStatement.setString(4, fname);
		insertStatement.setString(5, lname);

		log.debug(user +" " + pass + " " + fname  + " " + lname);// prints out info that passes in

		//execute insert
		insertStatement.executeUpdate();

		System.out.println("Administrator account is created");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}
	//return all employees in database
	public ArrayList<Employee> getAllEmployee() {
		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
		PreparedStatement ps = connect.prepareStatement("select * from admin");
		ResultSet rs = ps.executeQuery();
			//save db into a arraylist of object
		while (rs.next()) {
	        Employee emp = new Employee();
	        emp.seteId(rs.getInt(1));
	        emp.setFname(rs.getString(4));
	        emp.setLname(rs.getString(5));
		    emp.setUserName(rs.getString(2));
		     emp.setPassWord(rs.getString(3));
				log.debug(emp.toString());//print information retrieved

		     empList.add(emp);

		    }
		rs.close();
		ps.close();
        }
		catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return empList;
	}

	public Employee getEmployee(String username) {
		empList = getAllEmployee();// pulls database
		for(int i = 0; i < empList.size(); i++ )
		{
			if(( empList.get(i)).getUserName().equals(username))
			{
				return empList.get(i);
			}
		}		
		return null;
	}
	public boolean matchEmployee(String user, String pass)
	{
		empList = getAllEmployee();//retrieve data
		for (int i = 0; i < empList.size(); i++)
		{
			if(empList.get(i).getUserName().equals(user) && empList.get(i).getPassWord().equals(pass))
			{
				return true;
			}
		}
		return false;
	}
	public boolean matchEmployeeUsername(String user)
	{
		empList = getAllEmployee();//retrieve data
		for (int i = 0; i < empList.size(); i++)
		{
			if(empList.get(i).getUserName().equals(user))
			{
				return true;
			}
		}
		return false;
	}
	/*
	 *---------------------------------------- UPDATING CUSTOMER INFORMATION QUERIES -------------------------------------------------------------
	 * 
	 */
	public void updateCustomerUser(String user, int id) {
		String sql = "Update Customer set username = ? where cid = ?";

		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//prepare an insert statement into customer database
		PreparedStatement insertStatement = connect.prepareStatement(sql);
		insertStatement.setString(1, user);
		insertStatement.setInt(2, id);
		//execute insert
		insertStatement.executeUpdate();
	//	connect.commit();
		System.out.println("Account's username is updated");
		log.debug("running after account is successfully created");//message

		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }		
	}
	public void updateCustomerPass(String pass,int id) {
		String sql = "Update Customer set password = ? where cid = ?";
		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//prepare an insert statement into customer database
		PreparedStatement insertStatement = connect.prepareStatement(sql);
		insertStatement.setInt(2, id);
		insertStatement.setString(1, pass);
		
		//execute insert
		insertStatement.executeUpdate();
	//	connect.commit();
		System.out.println("Account's password is updated");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }		
	}
	public void updateCustomerLname(String lname, int id) {

		String sql = "Update Customer set lname = ? where cid = ?";

		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//prepare an insert statement into customer database
		PreparedStatement insertStatement = connect.prepareStatement(sql);
		insertStatement.setInt(2, id);
		insertStatement.setString(1, lname);
		//execute insert
		insertStatement.executeUpdate();
	//	connect.commit();
		System.out.println("Account's last name is updated");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }		
	}
	public void updateCustomerFname(String fname, int id) {

		String sql = "Update Customer set fname = ? where cid = ?";

		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//prepare an insert statement into customer database
		PreparedStatement insertStatement = connect.prepareStatement(sql);
		insertStatement.setInt(2, id);
		insertStatement.setString(1, fname);
		//execute insert
		insertStatement.executeUpdate();
	//	connect.commit();
		System.out.println("Account's first name is updated");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }		
	}
	public void updateCustomerAddr(String addr, int id) {

		String sql = "Update Customer set address = ? where cid = ?";

		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//prepare an insert statement into customer database
		PreparedStatement insertStatement = connect.prepareStatement(sql);
		insertStatement.setInt(2, 0);
		insertStatement.setString(1, addr);
		
		//execute insert
		insertStatement.executeUpdate();
	//	connect.commit();
		System.out.println("Account's address is updated");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }		
	}
	public void updateCustomerPhone(String phone,int id) {

		String sql = "Update Customer set phonenumber = ? where cid = ?";

		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//prepare an insert statement into customer database
		PreparedStatement insertStatement = connect.prepareStatement(sql);
		insertStatement.setInt(2, id);
		insertStatement.setString(1, phone);
		
		//execute insert
		insertStatement.executeUpdate();
	//	connect.commit();
		System.out.println("Account's phone number is updated");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }		
	}

}
