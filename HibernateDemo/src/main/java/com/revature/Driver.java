package com.revature;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Fish;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		init();
	}

	static void init() {
		// get session
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();

		Fish f1 = new Fish("Nemo", "Orange", 1);
		Fish f2 = new Fish("Dory", "Blue", 7);
		Fish f3 = new Fish("Marlin", "Orange", 10);
		Fish f4 = new Fish("Mr. Ray", "Blue", 12);

		s.save(f1);
		// ex of automatic dirty checking
		// updates in DB still
		f1.setColor("Yellow");

		s.save(f2);
		s.save(f3);
		s.save(f4);

		tx.commit();
		Query getFlashcards = s.createQuery("from FISH"); // Referring to Java name
		List<Fish> cards = getFlashcards.list();
		for (Fish f : cards)
			System.out.println("Fish: " + f);
		s.close();

	}
}
