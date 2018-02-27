package com.revature.dao;

import java.util.List;

import com.revature.pt1.Applier;

public interface ApplicantDao {
		//Save - insert a new Applicant
		public void insertNewApp(String username, String password, Double balance);
		
		//Retrieve all Applicants
		public List<Applier>retrieveAllApp();

		//Delete Applicant
		public void deleteAppByName(String name);

}