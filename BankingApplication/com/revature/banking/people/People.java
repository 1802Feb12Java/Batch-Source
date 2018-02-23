package com.revature.banking.people;

import java.util.ArrayList;
import java.util.Iterator;

import com.revature.database.DataStore;

/**
 * Contains methods for modifying people "Table" in the text file
 */
public class People {
	// private static final Logger logger = LogManager.getLogger(People.class);

	public static void addPersonToFile(Person p) {
		ArrayList<Person> people = DataStore.readPeopleFromFile();
		if (people == null)
			people = new ArrayList<Person>();
		people.add(p);
		DataStore.writePeopleToFile(people);
	}

	public static boolean removePersonFromFile(String personId) {

		ArrayList<Person> reqs = DataStore.readPeopleFromFile();
		int reqSize = reqs.size();
		Iterator<Person> iter = reqs.iterator();
		while (iter.hasNext())
			if (iter.next().getPersonId().equals(personId)) {
				iter.remove();
				// logger.info("Removed person with id " + personId + " from files");
				DataStore.writePeopleToFile(reqs);
			}
		if (reqs.size() == reqSize) {
			// logger.info("No person matched " + personId + " and, so none were removed
			// from file");
			return false;
		}
		return true;

	}

	public static boolean updatePerson(Person person) {
		ArrayList<Person> people = DataStore.readPeopleFromFile();
		for (int i = 0; i < people.size(); i++) {
			if (people.get(i).getPersonId().equals(person.getPersonId())) {
				people.remove(i);
				people.add(person);
				DataStore.writePeopleToFile(people);
				return true;
			}
		}
		return false;
	}

	public static Person getPersonByName(String name) {
		for (Person p : DataStore.readPeopleFromFile())
			if (p.getName().equals(name))
				return p;
		return null;
	}

	public static Person getPersonById(String id) {
		for (Person p : DataStore.readPeopleFromFile())
			if (p.getPersonId().equals(id))
				return p;
		return null;
	}
}
