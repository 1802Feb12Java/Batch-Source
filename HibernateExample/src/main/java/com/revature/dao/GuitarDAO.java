package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.revature.beans.Guitar;
import com.revature.util.HibernateUtil;

public class GuitarDAO {

	@SuppressWarnings("unchecked")
	public List<Guitar> getGuitars(){
		List<Guitar> guitars = new ArrayList<>();
		Session session = HibernateUtil.getSession();
		guitars = session.createQuery("from Guitar").list();
		session.close();
		return guitars;
	}
}
