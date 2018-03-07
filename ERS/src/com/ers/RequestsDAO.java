package com.ers;

public interface RequestsDAO {
	int getNumberOfPendingRequests();
	
	int getNumberOfApprovedRequests();
	
	int getTotalNumberOfRequests();
	
}