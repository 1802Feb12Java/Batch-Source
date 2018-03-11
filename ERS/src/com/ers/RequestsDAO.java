package com.ers;

public interface RequestsDAO {
	int getNumberOfPendingRequests(int id);
	
	int getNumberOfApprovedRequests(int id);
	
	int getTotalNumberOfRequests(int id);
	
}