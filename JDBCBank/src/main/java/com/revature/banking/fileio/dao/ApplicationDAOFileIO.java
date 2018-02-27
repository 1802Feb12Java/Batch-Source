package com.revature.banking.fileio.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.fileio.model.ApplicationFileIO;
import com.revature.banking.fileio.utilities.DAOUtilitiesFileIO;

public class ApplicationDAOFileIO implements InterfaceApplicationDAOFileIO{

	public ApplicationDAOFileIO() {
	}
	public static boolean saveApplication(ApplicationFileIO app) {
		return DAOUtilitiesFileIO.objectWrite(DAOUtilitiesFileIO.applicationsFolder + File.separator + app.getApplicationId(), app);
	}
	public static ApplicationFileIO getApplication(int applicationNumber) {
		ApplicationFileIO app = null;
		app = (ApplicationFileIO) DAOUtilitiesFileIO.objectRead(DAOUtilitiesFileIO.applicationsFolder + File.separator + applicationNumber);
		return app;
	}
	public List<ApplicationFileIO> getAllApplications() {
		List<ApplicationFileIO> applications= new ArrayList<ApplicationFileIO>();
		for (File file : new File(DAOUtilitiesFileIO.applicationsFolder).listFiles()) {
			ApplicationFileIO application = (ApplicationFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (application!=null)
				applications.add(application);
		}
		return applications;
	}
	public List<ApplicationFileIO> getAllProcessingApplications() {
		List<ApplicationFileIO> applications= new ArrayList<ApplicationFileIO>();
		for (File file : new File(DAOUtilitiesFileIO.applicationsFolder).listFiles()) {
			ApplicationFileIO application = (ApplicationFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (application!=null)
				if(application.getStatus().equals("Processing"))
					applications.add(application);
		}
		return applications;
	}

	public List<ApplicationFileIO> getAllApprovedApplications() {
		List<ApplicationFileIO> applications= new ArrayList<ApplicationFileIO>();
		for (File file : new File(DAOUtilitiesFileIO.applicationsFolder).listFiles()) {
			ApplicationFileIO application = (ApplicationFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (application!=null) {
				if(application.getStatus().split(" ")[0].equals("Approved"))
					applications.add(application);
			}
		}
		return applications;
	}

	public List<ApplicationFileIO> getAllDeniedApplications() {
		List<ApplicationFileIO> applications= new ArrayList<ApplicationFileIO>();
		for (File file : new File(DAOUtilitiesFileIO.applicationsFolder).listFiles()) {
			ApplicationFileIO application = (ApplicationFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (application!=null) {
				if(application.getStatus().split(" ")[0].equals("Denied"))
					applications.add(application);
			}
		}
		return applications;
	}

	public List<ApplicationFileIO> getApplicationsByCustomerId(int customerId) {
		List<ApplicationFileIO> applications= new ArrayList<ApplicationFileIO>();
		for (File file : new File(DAOUtilitiesFileIO.applicationsFolder).listFiles()) {
			ApplicationFileIO application = (ApplicationFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (application!=null) {
				if(application.getPrimaryCustomer()==customerId || application.getSecondaryCustomer()==customerId)
					applications.add(application);
			}
		}
		return applications;
	}
	public ApplicationFileIO getApplicationByApplicationId(int applicationId) {
		ApplicationFileIO application = ApplicationDAOFileIO.getApplication(applicationId);
		return application;
	}
	public ApplicationFileIO getApplicationByAccountId(int accountId) {
		for (File file : new File(DAOUtilitiesFileIO.applicationsFolder).listFiles()) {
			ApplicationFileIO application = (ApplicationFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (application!=null) {
				if(application.getAccountId()==accountId)
					return application;
			}
		}
		return null;
	}
	public boolean updateApplication(ApplicationFileIO application) {
		return ApplicationDAOFileIO.saveApplication(application);
	}

	public boolean addApplication(ApplicationFileIO application) {
		return ApplicationDAOFileIO.saveApplication(application);
	}

	public boolean deleteApplication(ApplicationFileIO application) {
		return DAOUtilitiesFileIO.deleteObject(DAOUtilitiesFileIO.applicationsFolder + File.separator + application.getApplicationId());
	}

	public int getNumApplications() {
		return new File(DAOUtilitiesFileIO.applicationsFolder).listFiles().length;
	}


}
