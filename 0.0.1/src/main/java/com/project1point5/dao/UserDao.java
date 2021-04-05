package com.project1point5.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

//	private User objectConstructor(ResultSet rs) throws SQLException {
//		return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
//				rs.getString(6), rs.getInt(7));
//	}

	@Override
	public List<User> getList() {
		try{
			Query query = session.createQuery("FROM User");
			List<User> l = query.list();
			return l;
		} catch(Exception e){
			return null;
		}

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
	}

	@Override
	public User getById(int id) {
		try{
			String hql = "FROM User u WHERE u.id = :userId";
			List<User> u = session.createQuery(hql)
					.setParameter("userId", id)
					.list();
			return u.get(0);
		} catch(Exception e){
			return null;
		}

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
	}

	@Override
	public List<User> getByUserId(int id) {
		// TODO Auto-generated method stub
		try{
			String hql = "FROM User u WHERE u.role_id = :id";
			List<User> u = session.createQuery(hql)
					.setParameter("id", id)
					.list();
			return u;
		} catch(Exception e){
			return null;
		}

	}

	@Override
	public User getByUsername(String username) {
		try{
			String hql = "FROM User u WHERE u.username = :username";
			List<User> u = session.createQuery(hql)
					.setParameter("username", username)
					.list();
			return u.get(0);
		}catch(Exception e){
			return null;
		}

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
	}

	@Override
	public void insert(User t) throws NoSuchAlgorithmException {
		try{
			User user = t;
			String pass = user.getPassword();
			String full = user.getUsername() + pass + "salt";
			//Let MessageDigest know that we want to hash using MD5
			MessageDigest m = MessageDigest.getInstance("md5");
			//Convert our full string to a byte array.
			byte[] messageDigest = m.digest(full.getBytes());
			//Convert our byte array into a signum representation of its former self.
			BigInteger n = new BigInteger(1, messageDigest);
			//Convert the whole array into a hexadecimal string.
			String hash = n.toString(16);
			while(hash.length() < 32) {
				hash = "0" + hash;
			}
			user.setPassword(hash);//hashing the password
			Transaction tr = session.beginTransaction();
			session.persist(user);
			session.flush();
			tr.commit();
			session.close();
		} catch(Exception e){

		}

	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		try{
			Transaction tr = session.beginTransaction();
			session.delete(t);
			session.flush();
			tr.commit();
			session.close();
		} catch(Exception e){
			
		}

	}
}
