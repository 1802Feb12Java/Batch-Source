package com.revature.driver;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.beans.Guitar;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		init();
	}

	@SuppressWarnings("unused")
	static void init() {

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();

		Guitar guitar1 = new Guitar("Les Paul", "Gibson", 6, 1800.45);
		Guitar guitar2 = new Guitar("RG350", "Ibanez", 7, 1234.56);
		Guitar guitar3 = new Guitar("Stratocaster", "Fender", 6, 78902);
		Guitar guitar4 = new Guitar("Other Guitar", "Vendor", 3, 902);
		session.save(guitar1);
		session.save(guitar2);
		session.save(guitar3);
		session.save(guitar4);
		transaction.commit();

		session.close();
	}

}
