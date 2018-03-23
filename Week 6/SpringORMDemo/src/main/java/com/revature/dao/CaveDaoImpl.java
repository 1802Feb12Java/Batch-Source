package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Cave;

@Transactional
@Component(value = "caveDao")
@Scope(value = "prototype")
public class CaveDaoImpl implements CaveDao {

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public List<Cave> getCaves() {
		Session s = sessionFactory.getCurrentSession();
		List<Cave> caves = s.createQuery("from Cave").list();
		for (Cave c : caves) {
			System.out.println(c);
		}
		return caves;
	}

	@Override
	public Cave getCaveById(int id) {
		Session s = sessionFactory.getCurrentSession();
		Cave c = (Cave) s.get(Cave.class, id); // what if this was load()?????
		return c;
	}

	@Override
	public int addCave(Cave c) {
		Session s = sessionFactory.getCurrentSession();
		int result = 0;// (int) s.save(c); //persist - no return type,
						// guaranteed to execute in tx
		s.saveOrUpdate(c);
		return result;
	}

	@Override
	public void updateCave(Cave c) {
		Session s = sessionFactory.getCurrentSession();
		// s.save(c);
		// s.update(c);
		// s.saveOrUpdate(c);
		s.merge(c);
	}

	@Override
	public void deleteCave(Cave c) {
		Session s = sessionFactory.getCurrentSession();
		s.delete(c);
	}

}
