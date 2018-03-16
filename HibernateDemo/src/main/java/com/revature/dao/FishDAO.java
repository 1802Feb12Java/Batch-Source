package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.revature.beans.Fish;
import com.revature.util.HibernateUtil;

public class FishDAO {

	@SuppressWarnings("unchecked")
	public List<Fish> getFishies() {
		List<Fish> fishes = new ArrayList<Fish>();
		Session session = HibernateUtil.getSession();
		fishes = session.createQuery("from Fish").list();
		session.close();
		return fishes;
	}
}
