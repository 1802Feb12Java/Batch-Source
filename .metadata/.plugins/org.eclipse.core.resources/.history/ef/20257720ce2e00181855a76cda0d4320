package com.revature.dao;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.revature.beans.Post;
import com.revature.util.HibernateUtil;

public class PostDAOClass implements PostDAO{

	// get all users posts
	
	public List<Post> getAllPosts() {
		String hql = "from Post";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		
		List<Post> list = query.list();
		
		if(list.isEmpty()) {
			session.close();
			return null;
		}
		
		session.close();
		
		return list;
	}
	
	
	// get the user posts
	
	public List<Post> getPostsByUserID(int userId) {
		String hql = "from Post"
				+ " WHERE USER_ID = :uid";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);

		query.setParameter("uid", userId);
		
		return query.list();
	}
	
	// user wants to create a post
	
	public boolean createPost(int post_id, String img_hash, String user_text, int user_id) {
		
		String hql = "INSERT INTO Post VALUES(:id, :hash, :text, :u_id)";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		
		query.setParameter("id", post_id);
		query.setParameter("hash", img_hash);
		query.setParameter("text", user_text);
		query.setParameter("u_id", user_id);
		
		int result = query.executeUpdate();
		session.close();
		
		if(result == 0) {
			
			System.out.println("Error. Was not able to create post.");
			return false;
		}
		
		System.out.println("Post was created successfully.");
		return true;
	}

}
