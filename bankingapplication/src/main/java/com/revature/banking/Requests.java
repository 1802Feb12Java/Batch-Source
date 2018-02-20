package com.revature.banking;

import java.util.ArrayList;
import java.util.Iterator;

import com.revature.database.DataStore;

/**
 * Contains methods for modifying the Requests "Table" in the text file
 */
public class Requests {
	// private static final Logger logger = LogManager.getLogger(Requests.class);

	public static void addRequestToFile(Request r) {
		ArrayList<Request> requests = DataStore.readRequestsFromFile();
		if (requests == null)
			requests = new ArrayList<Request>();
		requests.add(r);
		DataStore.writeRequestsToFile(requests);
	}

	public static boolean removeRequestFromFile(String userId) {

		ArrayList<Request> reqs = DataStore.readRequestsFromFile();
		int reqSize = reqs.size();
		Iterator<Request> iter = reqs.iterator();
		while (iter.hasNext())
			if (iter.next().getUserID().equals(userId)) {
				iter.remove();
				// logger.info("Removed request with id " + userId + " from files");
				DataStore.writeRequestsToFile(reqs);
			}
		if (reqs.size() == reqSize) {
			// logger.info("No Request matched " + userId + " and, so none were removed from
			// file");
			return false;
		}
		return true;

	}

	public static Request getRequstById(String userId) {
		for (Request r : DataStore.readRequestsFromFile())
			if (r.getUserID().equals(userId))
				return r;
		return null;
	}

}
