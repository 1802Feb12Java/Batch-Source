package JDBCBank.JDBCBank.customer.bean;

import java.util.ArrayList;

public interface CustomerDao {

	public ArrayList<Customer> getAllCustomer();
	public Customer getCustomer(String name);
	public void updateCustomer(Customer c);
	public void deleteCustomer(int cid);
	}
