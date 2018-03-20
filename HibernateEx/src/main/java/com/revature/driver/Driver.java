package com.revature.driver;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Guitar;
import com.revature.util.HibernateUtil;

public class Driver {

	
	
	public static void main(String[] args) {
		init();

	}

	static void init() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Guitar g1 = new Guitar("Gibson", "Les Paul", 6, "Red", 1800);
		Guitar g2 = new Guitar("Ibenez", "RG350", 7, "Yellow", 1234.56);
		Guitar g3 = new Guitar("Fender", "Stratocaster", 42, "Rainbow", 8);
		
		session.save(g1);
		session.save(g2);
		session.save(g3);
		
		tx.commit();
		session.close();
	}
	
}
