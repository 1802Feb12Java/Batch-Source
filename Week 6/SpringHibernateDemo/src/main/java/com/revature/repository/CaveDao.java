package com.revature.repository;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.model.Cave;

@Repository
@Transactional
public class CaveDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Cave findCave(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return (Cave) session.get(Cave.class, id);
	}

}
