package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.revature.beans.Guitar;
import com.revature.util.HibernateUtil;

public class GuitarDAO {

	@SuppressWarnings("unchecked")
	public List<Guitar> getGuitars(){
<<<<<<< HEAD
		List<Guitar> guitars = new ArrayList<>();
=======
		List<Guitar> guitars = new ArrayList<Guitar>()	;
>>>>>>> KnightenM
		Session session = HibernateUtil.getSession();
		guitars = session.createQuery("from Guitar").list();
		session.close();
		return guitars;
<<<<<<< HEAD
	}
=======
		}
>>>>>>> KnightenM
}
