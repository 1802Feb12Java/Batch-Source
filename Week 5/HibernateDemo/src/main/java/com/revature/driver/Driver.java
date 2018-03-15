package com.revature.driver;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Bat;
import com.revature.beans.Bear;
import com.revature.beans.Cave;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		init();
		//BearDAO dao = new BearDAO();
		//System.out.println(dao.getBears());
		//System.out.println(dao.getBearByName("Yogi"));
		//Bear b = new Bear("Baloo", 75, 300);
		//int bearId = dao.saveBear(b);
		//System.out.println(b.toString());
		//System.out.println("Fed bear " + dao.feedBear(bearId, 100));
		//System.out.println(b.toString());
		//usingCriteria();
		

	}
	
	static void init() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Cave c1 = new Cave("Gloworm Cave");
		Cave c2 = new Cave("Howe's Caverns");
		
		Bear b1 = new Bear("Winnie", 50, 10, c1);
		Bear b2 = new Bear("Yogi", 25, 400, c2);
		Bear b3 = new Bear("Smokey", 100, 300, c1);
		Bear b4 = new Bear("RevaBear", 2, 600, c2);
		
		Bat bat1 = new Bat("Batman", 7, c1);
		Bat bat2 = new Bat("Manbat", 9, c2);
		
		session.save(c1);
		session.save(c2);
		session.save(b1);
		session.save(b2);
		session.save(b3);
		session.save(b4);
		session.save(bat1);
		session.save(bat2);
		
		findBearByName("Winnie", session);
		
		tx.commit();
		session.close();
	}
	
	static void findBearByName(String name, Session session) {
		List<Bear> bears = session.getNamedQuery("findBearByName").setString("namevar", name).list();
		System.out.println(bears.toString());
	}

	@SuppressWarnings("unchecked")
	static void usingCriteria() {
		Session session = HibernateUtil.getSession();
		//List<Bear> bears = session.createCriteria(Bear.class).add(Restrictions.like("name", "B%")).list();
		
		List<Bear> bears = session.createCriteria(Bear.class)
				.add(Restrictions.between("weight", 350, 700))
				.addOrder(Order.asc("weight")).list();
		
		System.out.println(bears.toString());
		
		Criteria criteria = session.createCriteria(Bear.class);
		criteria.setProjection(Projections.rowCount());
		List rowCount = criteria.list();
		System.out.println("Number of Bears = " + rowCount.get(0));
		
	}

}
