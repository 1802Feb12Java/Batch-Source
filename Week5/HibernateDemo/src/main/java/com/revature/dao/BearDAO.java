package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Bear;
import com.revature.util.HibernateUtil;

public class BearDAO {
	
	public List<Bear> getBears() {
		List<Bear> bears = new ArrayList<>();
		Session session = HibernateUtil.getSession();
		bears = session.createQuery("from Bear").list();
		session.close();
		return bears;
	}
	
	@SuppressWarnings("unchecked")
	public Bear getBearByName(String bearName) {
		Bear bear = null;
		List<Bear> bears = new ArrayList<>();
		Session session = HibernateUtil.getSession();
		bears = session.createQuery("from Bear where name = :namevar")
				.setString("namevar", bearName).list();
		if (!bears.isEmpty()) {
			bear = bears.get(0);
		}
		return bear;
	}
	
	public int saveBear(Bear b) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int result = (int) session.save(b);
		tx.commit();
		session.close();
		return result;
	}
	
	public void persistBear(Bear b) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.persist(b);
		tx.commit();
		session.close();
	}
	
	public int feedBear(int bearId, int amt) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		Bear b = (Bear) session.load(Bear.class, bearId);
		
		b.setWeight(b.getWeight() + amt);
		
		session.merge(b);
		
		tx.commit();
		session.close();
		return amt;
	}

}
