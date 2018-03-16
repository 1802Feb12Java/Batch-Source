package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Where we get all of our sessions from
 */
public class HibernateUtil {

	// makes sessions
	public static SessionFactory sessionFactory(String filename) {
		Configuration config = new Configuration().configure(filename);
		// make a service registry
		ServiceRegistry serviceR = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		return config.buildSessionFactory(serviceR);
	}

	public static Session getSession() {
		return sessionFactory("hibernate.cfg.xml").openSession();

	}
}
