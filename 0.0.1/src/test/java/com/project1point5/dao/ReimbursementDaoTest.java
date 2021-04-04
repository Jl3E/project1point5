package com.project1point5.dao;

import com.project1point5.model.Reimbursement;
import com.project1point5.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ReimbursementDaoTest {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    Session session = factory.openSession();
    ReimbursementDao rd = new ReimbursementDao();
    UserDao ud = new UserDao();

    @Test
    public void getListTest() {
        Query query = session.createQuery("FROM Reimbursement");
        List<Reimbursement> l = query.list();
        int actual = l.get(0).getId();
        Assert.assertEquals(1,actual);
    }

    @Test
    public void deleteTest(){
        User u = new User(4,"job","jobPass","j","job","job@mail.com",0);
        Reimbursement r = new Reimbursement(4,50, null,null,"something",1,u,0,1);
        rd.delete(r);
        ud.delete(u);

        Reimbursement tr;
        try{
            tr = rd.getById(4);
        } catch(Exception e){
            tr = null;
        }

        Assert.assertEquals(null,tr);
    }

    @Test
    public void insertTest(){
        User u = new User(4,"job","jobPass","j","job","job@mail.com",0);
        Reimbursement r = new Reimbursement(4,50, null,null,"something",1,u,0,1);

        Transaction t = session.beginTransaction();

        // add the daos for insert here.

        session.flush();
        t.commit();
        session.close();

        Reimbursement tr;

        try{
            tr = rd.getById(4);
        } catch(Exception e){
            tr = null;
        }

        Assert.assertEquals(r,tr);
    }

    @Test //TODO: CREATE INSERT FIRST THEN USE INSERT WITH A NEW REIMBURSEMTN OBJECT, THEN GET THAT USER WITH SAID ID.
    public void getByIdTest() {
//        String hql = "FROM Reimbursement R WHERE R.id = :reimbursementId";
//        int id = 1;
//        List<Reimbursement> r = session.createQuery(hql)
//                .setParameter("reimbursementId", id)
//                .list();
//        Reimbursement reimbursement = r.get(0);
//        String
//        Assert.assertEquals();
    }
}
