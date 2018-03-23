package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Bear;

@Transactional
@Component(value="bearDao")
@Scope(value="prototype")
public class BearDaoImpl implements BearDao {

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public List<Bear> getBears() {
		Session s = sessionFactory.getCurrentSession();
		List<Bear> Bears = s.createQuery("from Bear").list();
		for (Bear b : Bears) {
			System.out.println(b);
		}
		s.close();
		return Bears;
	}

	@Override
	public Bear getBearById(int id) {
		Session s = sessionFactory.getCurrentSession();
		Bear b = (Bear) s.get(Bear.class, id); // what if this was load()?????
		s.close();
		return b;
	}

	@Override
	public int addBear(Bear b) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		int result = (int) s.save(b);
		tx.commit();
		s.close();
		return result;
	}

	@Override
	public void updateBear(Bear b) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		s.merge(b);
		tx.commit();
		s.close();
	}

	@Override
	public void deleteBear(Bear b) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		s.delete(b);
		tx.commit();
		s.close();
	}

}
