package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Animal;
import com.revature.beans.Bat;

@Transactional
@Component(value="batDao")
@Scope(value="prototype")
public class BatDaoImpl implements BatDao {
	
	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public List<Bat> getBats() {
		Session s = sessionFactory.getCurrentSession();
		List<Bat> bats = s.createQuery("from Bat").list();
			for(Bat b : bats){
				System.out.println(b);
			}
		s.close();
		return bats;
	}

	@Override
	public Bat getBatById(int id) {
		Session s = sessionFactory.getCurrentSession();
		Bat b = (Bat) s.get(Animal.class, id); //what if this was load()?????
		s.close();
		return b;
	}

	@Override
	public int addBat(Bat b) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		int result = 0;//(int) s.save(b);
		s.saveOrUpdate(b);
		tx.commit();
		s.close();
		return result;
	}

	@Override
	public void updateBat(Bat b) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
			s.merge(b);
		tx.commit();
		s.close();
	}

	@Override
	public void deleteBat(Bat b) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
			s.delete(b);
		tx.commit();
		s.close();
	}

}
