package com.revature.banking.fileio.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.fileio.model.CustomerFileIO;
import com.revature.banking.fileio.utilities.DAOUtilitiesFileIO;

public class CustomerDAOFileIO extends UserDAOFileIO implements InterfaceCustomerDAOFileIO {

	public CustomerDAOFileIO() {
	}
	public static CustomerFileIO getCustomer(int customerId) {
		CustomerFileIO customer = null;
		customer = (CustomerFileIO) DAOUtilitiesFileIO.objectRead(DAOUtilitiesFileIO.customersFolder + File.separator + customerId);
		return customer;
	}
	public static boolean saveCustomer(CustomerFileIO customer) {
		return DAOUtilitiesFileIO.objectWrite(DAOUtilitiesFileIO.customersFolder + File.separator + customer.getUserId(), customer);
	}
	public List<CustomerFileIO> getAllCustomers() {
		List <CustomerFileIO> customers = new ArrayList<CustomerFileIO>();
		for (File file : new File(DAOUtilitiesFileIO.customersFolder).listFiles()){
			CustomerFileIO customer = (CustomerFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (customer!=null)
				customers.add(customer);
		}
		return customers;
	}
	public List<CustomerFileIO> getCustomersByName(String firstName, String lastName) {
		List <CustomerFileIO> customers = new ArrayList<CustomerFileIO>();
		for (File file : new File(DAOUtilitiesFileIO.customersFolder).listFiles()){
			CustomerFileIO customer = (CustomerFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (customer!=null)
				if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName))
					customers.add(customer);
		}
		return customers;
	}
	public CustomerFileIO getCustomerByCustomerId(int customerId) {
		CustomerFileIO customer = CustomerDAOFileIO.getCustomer(customerId);
		return customer;
	}
	public boolean updateCustomer(CustomerFileIO customer) {
		return CustomerDAOFileIO.saveCustomer(customer);
	}

	public boolean addCustomer(CustomerFileIO customer) {
		return CustomerDAOFileIO.saveCustomer(customer);
	}

	public boolean deleteCustomer(CustomerFileIO customer) {
		return DAOUtilitiesFileIO.deleteObject(DAOUtilitiesFileIO.customersFolder + File.separator + customer.getUserId());
	}

	public int getNumCustomers() {
		return new File(DAOUtilitiesFileIO.customersFolder).listFiles().length;
	}

	public boolean customerExists(int customerId) {
		for (File file : new File(DAOUtilitiesFileIO.customersFolder).listFiles()){
			CustomerFileIO customer = (CustomerFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (customer!=null)
				if (customer.getUserId()==customerId)
					return true;
		}
		return false;
	}
}
