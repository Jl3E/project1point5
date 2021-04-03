package com.project1point5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project1point5.model.Reimbursement;
import org.apache.log4j.Logger;

import com.project1point5.model.User;
import com.project1point5.util.ConnectionUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class UserDao implements GenericDao <User> {
	private static final Logger LOGGER = Logger.getLogger(UserDao.class);
	SessionFactory factory = new Configuration().configure().buildSessionFactory();
	Session session = factory.openSession();

	private User objectConstructor(ResultSet rs) throws SQLException {
		return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getInt(7));
	}

	@Override
	public List<User> getList() {
		Query query = session.createQuery("FROM User");
		List<User> l = query.list();
//		List<User> l = new ArrayList<User>();
//
//		try (Connection c = ConnectionUtil.getInstance().getConnection()) {
//			String qSql = "SELECT * FROM ers_users";
//			Statement s = c.createStatement();
//			ResultSet rs = s.executeQuery(qSql);
//
//			while(rs.next()) {
//				l.add(objectConstructor(rs));
//			}
//			LOGGER.debug("A list of users was retrieved from the database.");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			LOGGER.error("An attempt to get all users from the database failed.");
//		}
		return l;
	}

	@Override
	public User getById(int id) {
		String hql = "FROM User u WHERE u.id = :userId";
		List<User> u = session.createQuery(hql)
				.setParameter("userId", id)
				.list();
//		User u = null;
//
//		try(Connection c = ConnectionUtil.getInstance().getConnection()) {
//			String qSql = "SELECT * FROM ers_users WHERE ers_users_id = ?";
//			PreparedStatement ps = c.prepareStatement(qSql);
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//
//			if(rs.next())
//				u = objectConstructor(rs);
//
//			LOGGER.debug("Information about user ID " + id + " was retrieved from the database.");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			LOGGER.error("An attempt to get info about user ID " + id + " from the database failed.");
//		}
		return u.get(0);
	}

	@Override
	public List<User> getByUserId(int id) {
		// TODO Auto-generated method stub
		String hql = "FROM User u WHERE u.role_id = :id";
		List<User> u = session.createQuery(hql)
				.setParameter("id", id)
				.list();
		return u;
	}

	@Override
	public User getByUsername(String username) {
		String hql = "FROM User u WHERE u.username = :username";
		List<User> u = session.createQuery(hql)
				.setParameter("username", username)
				.list();

//		User u = null;
//
//		try(Connection c = ConnectionUtil.getInstance().getConnection()) {
//			String qSql = "SELECT * FROM ers_users WHERE ers_username = ?";
//			PreparedStatement ps = c.prepareStatement(qSql);
//			ps.setString(1, username.toLowerCase());
//			ResultSet rs = ps.executeQuery();
//
//			if(rs.next()) {
//				//System.out.println("User object was created!");
//				u = objectConstructor(rs);
//			}
//
//			LOGGER.debug("Information about username " + username + " was retrieved from the database.");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			LOGGER.error("An attempt to get info about username " + username + " from the database failed.");
//		}
		return u.get(0);
	}

	@Override
	public void insert(User t) {
		// TODO Auto-generated method stub
		Transaction tr = session.beginTransaction();
		session.persist(t);
		session.flush();
		tr.commit();
		session.close();
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		Transaction tr = session.beginTransaction();
		session.delete(t);
		session.flush();
		tr.commit();
		session.close();
	}
}
