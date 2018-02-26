package com.revature.banking.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import com.revature.banking.model.Customer;
import com.revature.banking.utilities.DAOUtilities;

public class CustomerDAO extends UserDAO implements InterfaceCustomerDAO {

	public CustomerDAO() {
	}
	public static Customer getCustomer(int customerId) {
		Customer customer = null;
		customer = (Customer) DAOUtilities.objectRead(DAOUtilities.customersFolder + File.separator + customerId);
		return customer;
	}
	public static boolean saveCustomer(Customer customer) {
		return DAOUtilities.objectWrite(DAOUtilities.customersFolder + File.separator + customer.getUserId(), customer);
	}
	public List<Customer> getAllCustomers() {
		List <Customer> customers = new ArrayList<Customer>();
		for (File file : new File(DAOUtilities.customersFolder).listFiles()){
			Customer customer = (Customer) DAOUtilities.objectReadFile(file);
			if (customer!=null)
				customers.add(customer);
		}
		return customers;
	}
	public List<Customer> getCustomersByName(String firstName, String lastName) {
		List <Customer> customers = new ArrayList<Customer>();
		for (File file : new File(DAOUtilities.customersFolder).listFiles()){
			Customer customer = (Customer) DAOUtilities.objectReadFile(file);
			if (customer!=null)
				if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName))
					customers.add(customer);
		}
		return customers;
	}
	public Customer getCustomerByCustomerId(int customerId) {
		Customer customer = CustomerDAO.getCustomer(customerId);
		return customer;
	}
	public boolean updateCustomer(Customer customer) {
		return CustomerDAO.saveCustomer(customer);
	}

	public boolean addCustomer(Customer customer) {
		return CustomerDAO.saveCustomer(customer);
	}

	public boolean deleteCustomer(Customer customer) {
		return DAOUtilities.deleteObject(DAOUtilities.customersFolder + File.separator + customer.getUserId());
	}

	public int getNumCustomers() {
		return new File(DAOUtilities.customersFolder).listFiles().length;
	}

	public boolean customerExists(int customerId) {
		for (File file : new File(DAOUtilities.customersFolder).listFiles()){
			Customer customer = (Customer) DAOUtilities.objectReadFile(file);
			if (customer!=null)
				if (customer.getUserId()==customerId)
					return true;
		}
		return false;
	}
}
