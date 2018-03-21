package com.revature.util;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory(String filename) {
		Configuration config = new Configuration().configure(filename);
		ServiceRegistry serviceR = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		return config.buildSessionFactory(serviceR);
	}
	
	public static Session getSession() {
		return sessionFactory("hibernate.config.xml").openSession();
	}
}
