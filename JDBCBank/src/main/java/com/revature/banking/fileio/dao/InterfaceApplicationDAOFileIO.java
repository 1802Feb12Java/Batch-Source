package com.revature.banking.fileio.dao;

import java.util.List;

import com.revature.banking.fileio.model.ApplicationFileIO;

public interface InterfaceApplicationDAOFileIO {
	public List<ApplicationFileIO> getAllApplications();
	public List<ApplicationFileIO> getAllProcessingApplications();
	public List<ApplicationFileIO> getAllApprovedApplications();
	public List<ApplicationFileIO> getAllDeniedApplications();
	public List<ApplicationFileIO> getApplicationsByCustomerId(int customerId);
	public ApplicationFileIO getApplicationByApplicationId(int applicationId);
	public ApplicationFileIO getApplicationByAccountId(int accountId);
	public boolean updateApplication(ApplicationFileIO application);
	public boolean deleteApplication(ApplicationFileIO application);
	public int getNumApplications();
}
