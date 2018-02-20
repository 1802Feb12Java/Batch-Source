package com.revature.banking.people;

import java.util.ArrayList;
import java.util.Iterator;

import com.revature.database.DataStore;

/**
 * User Authentication class
 */
public class Users {
	// private static final Logger logger = LogManager.getLogger(Users.class);

	public static User checkCredentials(String username, String password) {
		// check username exists first.
		ArrayList<User> users = DataStore.readUsersFromFile();
		if (users == null)
			return null;
		for (User u : users) {
			if (u.getUsername().equals(username))
				// check password
				if (u.getPassword().equals(password)) {
					// logger.info("Correct Password entered");
					return u;
				}
		}
		return null;
	}

	public static void addUser(User user) {

		ArrayList<User> userList = DataStore.readUsersFromFile();
		if (userList == null)
			userList = new ArrayList<User>();

		// commit user to storage
		userList.add(user);
		DataStore.writeUsersToFile(userList);
	}

	public static Person getPerson(User u) {
		ArrayList<Person> people = DataStore.readPeopleFromFile();
		for (Person p : people)
			if (p.getPersonId().equals(u.getPersonID()))
				return p;
		return null;
	}

	public static User getUser(Person p) {
		for (User u : DataStore.readUsersFromFile())
			if (p.getPersonId().equals(u.getPersonID()))
				return u;
		return null;
	}

	public static User getUserById(String id) {
		for (User u : DataStore.readUsersFromFile())
			if (u.getPersonID().equals(id))
				return u;
		return null;
	}

	public static boolean removeUserFromFile(String userId) {

		ArrayList<User> users = DataStore.readUsersFromFile();
		int userSize = users.size();
		Iterator<User> iter = users.iterator();
		while (iter.hasNext())
			if (iter.next().getPersonID().equals(userId)) {
				iter.remove();
				// logger.info("Removed person with id " + userId + " from files");
				DataStore.writeUsersToFile(users);
			}
		if (users.size() == userSize) {
			// logger.info("No person matched " + userId + " and, so none were removed from
			// file");
			return false;
		}
		return true;

	}

}
