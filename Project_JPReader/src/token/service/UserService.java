package token.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import token.entity.User;

@Transactional
@Component
public class UserService {
	@Autowired
	SessionFactory factory;
	
	public void insert(User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(user);
			t.commit();
		} 
		catch (Exception e) {
			t.rollback();
			throw new RuntimeException(e);
		}
		finally{
			session.close();
		}
	}
	
	public void update(User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(user);
			t.commit();
		} 
		catch (Exception e) {
			t.rollback();
			throw new RuntimeException(e);
		}
		finally{
			session.close();
		}
	}
	
	public void delete(User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(user);
			t.commit();
		} 
		catch (Exception e) {
			t.rollback();
			throw new RuntimeException(e);
		}
		finally{
			session.close();
		}
	}
	
	public void refresh(User user) {
		Session session = factory.getCurrentSession();
		session.refresh(user);
	}
	
	public User get(String id) {
		Session session = factory.getCurrentSession();
		User customer = (User) session.get(User.class, id);
		return customer;
	}
	
	public List<User> list() {
		String hql = "FROM Customer";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		return list;
	}
}
