package JDBCBank.JDBCBank.customeraccount.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBCBank.JDBCBank.oraclejdbc.ConnFactory;

public class CustomerAccountDaoImpl implements CustomerAccounDao {

	//ArrayList<CustomerAccount> caList = new ArrayList();
	public CustomerAccountDaoImpl()//default constructor
	{
		
	}
	public CustomerAccountDaoImpl(CustomerAccount ca)
	{
		//caList.add(ca);
	}
	public boolean checkBankAccountID(int caid) {
		ArrayList<CustomerAccount> caList = getEverythingInCustomerAccount();
		for(int i = 0; i < caList.size(); i++) {
			if(caid == caList.get(i).getCaid()) {
				return true;
			}
		}
		return false;

	}
	public ArrayList<CustomerAccount> getEverythingInCustomerAccount()
	{
		ArrayList<CustomerAccount> caList = new ArrayList();

		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
		PreparedStatement ps = connect.prepareStatement("select * from customeraccount");
		ResultSet rs = ps.executeQuery();
			//save db into a arraylist of object
		while (rs.next()) {
			CustomerAccount ca = new CustomerAccount();
		     ca.setBalance(rs.getDouble(3));
		     ca.setCid(rs.getInt(2));
		     ca.setCaid(rs.getInt(1));
		     caList.add(ca);

		    }
		rs.close();
		ps.close();
        }
		catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }	
		return caList;
	}
	//get all customers accounts
	public ArrayList<CustomerAccount> getAllCustomerAccount(int id) {
		ArrayList<CustomerAccount> caList = new ArrayList();

		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
		PreparedStatement ps = connect.prepareStatement("select * from customeraccount where cid = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
			//save db into a arraylist of object
		while (rs.next()) {
			CustomerAccount ca = new CustomerAccount();
		     ca.setBalance(rs.getDouble(3));
		     ca.setCid(rs.getInt(2));
		     ca.setCaid(rs.getInt(1));
		     caList.add(ca);

		    }
		rs.close();
		ps.close();
        }
		catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }	
		return caList;
	}
	
	//return customer account object for other functionality
	public CustomerAccount getCustomerAccount(int cid) {
		ArrayList<CustomerAccount> caList = new ArrayList();

		caList = getAllCustomerAccount(cid);
		
		for(int i = 0; i < caList.size(); i++)
		{
			if(caList.get(i).getCid() == cid)
					{
						return caList.get(i);
					}
		}
		return null;
	}
	//get 1 id for withdraw and deposit
	public CustomerAccount getMatchingAccountNumber(int caid, int cid) {
		ArrayList<CustomerAccount> caList = getAllCustomerAccount(cid);
		for(int i = 0; i < caList.size(); i++) {
			if(caid == caList.get(i).getCaid() && cid == caList.get(i).getCid()) {
				return caList.get(i);
			}
		}
		return null;
	}
	public boolean checkCustomerAccountBalance(int caid) {
		ArrayList<CustomerAccount> caList = new ArrayList();

		caList = getAllCustomerAccount(caid);
		
		for(int i = 0; i < caList.size(); i++)
		{
			if(caList.get(i).getBalance() != 0 )//check if account has balance
				{
						return true;
				}
		}
		return false;

	}
	public void updateCustomerAccount(CustomerAccount ca) {
		// TODO Auto-generated method stub
		
	}

	//delete accounts
	public void deleteCustomerAccount(int accountid) {
		String sql = "delete from customeraccount where cid = ?";//delete foreign keys
		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database

		try {
			//prepare an insert statement into customer database
		PreparedStatement insertStatement = connect.prepareStatement(sql);
		insertStatement.setInt(1, accountid);
	
		
		//execute insert
		insertStatement.executeUpdate();
	//	connect.commit();
		System.out.println("Accounts have been deleted");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }	
		
	}

}
