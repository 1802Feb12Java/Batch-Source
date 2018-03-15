package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory(String filename) {
		Configuration config = new Configuration().configure(filename);
		ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		return config.buildSessionFactory(sr);
		
	}
	
	public static Session getSession() {
		return sessionFactory("hibernate.cfg.xml").openSession();
	}
	
	
	
}//end class
