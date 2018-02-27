package com.revature.banking.jdbc.dao;

import java.util.List;

import com.revature.banking.jdbc.model.Application;

public interface InterfaceApplicationDAO {
	public List<Application> getAllApplications();
	public List<Application> getAllProcessingApplications();
	public List<Application> getAllApprovedApplications();
	public List<Application> getAllDeniedApplications();
	public List<Application> getApplicationsByCustomerId(int customerId);
	public Application getApplicationByApplicationId(int applicationId);
	public Application getApplicationByAccountId(int accountId);
	public boolean updateApplication(Application application);
	public boolean deleteApplication(Application application);
	public int getNumApplications();
}
