package com.project1point5.dao;

import com.project1point5.model.Reimbursement;
import com.project1point5.model.User;
import com.project1point5.service.ReimbursementService;
import com.project1point5.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class ReimbursementDaoTest {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    Session session = factory.openSession();
    ReimbursementDao rd = new ReimbursementDao();
    UserDao ud = new UserDao();
    ReimbursementService rs = new ReimbursementService();
    User u = new User(4,"job","jobPass","j","job","job@mail.com",0);
    Reimbursement r = new Reimbursement(4,50, null,null,"something",4,u,0,1);

    @Test
    public void getListTest() {
        Query query = session.createQuery("FROM Reimbursement");
        List<Reimbursement> l = query.list();
        int actual = l.get(0).getId();
        Assert.assertEquals(1,actual);
    }

    @Test
    public void deleteTest(){
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
    public void insertTest() throws NoSuchAlgorithmException {
        ud.insert(u);
        rd.insert(r);

        Reimbursement tr = rs.getReimbursementById(4);
        System.out.println(tr.toString());

        Assert.assertEquals(4,tr.getId());
    }

    @Test
    public void getByIdTest() {
        String hql = "FROM Reimbursement R WHERE R.id = :reimbursementId";
        int id = r.getId();
        List<Reimbursement> reim = session.createQuery(hql)
                .setParameter("reimbursementId", id)
                .list();
        Reimbursement reimbursement = reim.get(0);
        String actual = reimbursement.getDescription();
        Assert.assertEquals("something", actual);
    }

    @Test
    public void getByUserIdTest(){
        List<Reimbursement> values = rd.getByUserId(4);
        int actual = values.get(0).getId();

        Assert.assertEquals(4,actual);
    }

    @Test
    public void updateListTest(){
        int[][] values = { {4}, {} }; //first array takes accepted ReimbursementId's the second, defined.
        rd.updateList(values, 1);
        Reimbursement tr = rs.getReimbursementById(4);
        int actual = tr.getType_id();

        Assert.assertEquals(1,actual);
    }
}
