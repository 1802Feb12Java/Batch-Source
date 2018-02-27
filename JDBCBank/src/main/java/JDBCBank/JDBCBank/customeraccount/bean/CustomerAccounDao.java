package JDBCBank.JDBCBank.customeraccount.bean;

import java.util.ArrayList;

public interface CustomerAccounDao {

	public ArrayList<CustomerAccount> getAllCustomerAccount(int id);
	public CustomerAccount getCustomerAccount(int cid);
	public void updateCustomerAccount(CustomerAccount ca);
	public void deleteCustomerAccount(int accountid);
	
}
