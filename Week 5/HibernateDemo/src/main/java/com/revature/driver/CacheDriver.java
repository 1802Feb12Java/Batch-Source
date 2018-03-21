package com.revature.driver;

import org.hibernate.Session;

import com.revature.beans.Bear;
import com.revature.util.HibernateUtil;

public class CacheDriver {
	public static void main(String[] args) {
		Session session1 = HibernateUtil.getSession();
		Session session2 = HibernateUtil.getSession();
		
		Bear b1 = (Bear) session1.load(Bear.class, 1);
		System.out.println(b1.toString());
		
		Bear b2 = (Bear) session2.load(Bear.class, 1);
		System.out.println(b2.toString());
	}
}
