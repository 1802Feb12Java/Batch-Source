package com.revature.banking.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.model.Application;
import com.revature.banking.utilities.DAOUtilities;

public class ApplicationDAO implements InterfaceApplicationDAO{

	public ApplicationDAO() {
	}
	public static boolean saveApplication(Application app) {
		return DAOUtilities.objectWrite(DAOUtilities.applicationsFolder + File.separator + app.getApplicationId(), app);
	}
	public static Application getApplication(int applicationNumber) {
		Application app = null;
		app = (Application) DAOUtilities.objectRead(DAOUtilities.applicationsFolder + File.separator + applicationNumber);
		return app;
	}
	public List<Application> getAllApplications() {
		List<Application> applications= new ArrayList<Application>();
		for (File file : new File(DAOUtilities.applicationsFolder).listFiles()) {
			Application application = (Application) DAOUtilities.objectReadFile(file);
			if (application!=null)
				applications.add(application);
		}
		return applications;
	}
	public List<Application> getAllProcessingApplications() {
		List<Application> applications= new ArrayList<Application>();
		for (File file : new File(DAOUtilities.applicationsFolder).listFiles()) {
			Application application = (Application) DAOUtilities.objectReadFile(file);
			if (application!=null)
				if(application.getStatus().equals("Processing"))
					applications.add(application);
		}
		return applications;
	}

	public List<Application> getAllApprovedApplications() {
		List<Application> applications= new ArrayList<Application>();
		for (File file : new File(DAOUtilities.applicationsFolder).listFiles()) {
			Application application = (Application) DAOUtilities.objectReadFile(file);
			if (application!=null) {
				if(application.getStatus().split(" ")[0].equals("Approved"))
					applications.add(application);
			}
		}
		return applications;
	}

	public List<Application> getAllDeniedApplications() {
		List<Application> applications= new ArrayList<Application>();
		for (File file : new File(DAOUtilities.applicationsFolder).listFiles()) {
			Application application = (Application) DAOUtilities.objectReadFile(file);
			if (application!=null) {
				if(application.getStatus().split(" ")[0].equals("Denied"))
					applications.add(application);
			}
		}
		return applications;
	}

	public List<Application> getApplicationsByCustomerId(int customerId) {
		List<Application> applications= new ArrayList<Application>();
		for (File file : new File(DAOUtilities.applicationsFolder).listFiles()) {
			Application application = (Application) DAOUtilities.objectReadFile(file);
			if (application!=null) {
				if(application.getPrimaryCustomer()==customerId || application.getSecondaryCustomer()==customerId)
					applications.add(application);
			}
		}
		return applications;
	}
	public Application getApplicationByApplicationId(int applicationId) {
		Application application = ApplicationDAO.getApplication(applicationId);
		return application;
	}
	public Application getApplicationByAccountId(int accountId) {
		for (File file : new File(DAOUtilities.applicationsFolder).listFiles()) {
			Application application = (Application) DAOUtilities.objectReadFile(file);
			if (application!=null) {
				if(application.getAccountId()==accountId)
					return application;
			}
		}
		return null;
	}
	public boolean updateApplication(Application application) {
		return ApplicationDAO.saveApplication(application);
	}

	public boolean addApplication(Application application) {
		return ApplicationDAO.saveApplication(application);
	}

	public boolean deleteApplication(Application application) {
		return DAOUtilities.deleteObject(DAOUtilities.applicationsFolder + File.separator + application.getApplicationId());
	}

	public int getNumApplications() {
		return new File(DAOUtilities.applicationsFolder).listFiles().length;
	}


}
