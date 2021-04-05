package com.project1point5.dao;

import com.project1point5.model.Reimbursement;
import com.project1point5.model.User;
import com.project1point5.service.ReimbursementService;
import com.project1point5.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserDaoTest {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    Session session = factory.openSession();
    UserDao ud = new UserDao();
    UserService us = new UserService();
    User u = new User(5,"some guy","guyPass","some","guy","guy@mail.com",0);

    @Test
    public void getListTest() {
        Query query = session.createQuery("FROM User");
        List<User> l = query.list();
        int actual = l.get(0).getUser_id();
        Assert.assertEquals(1,actual);
    }

    @Test
    public void deleteTest(){
        ud.delete(u);

        User user;
        try{
            user = ud.getById(4);
        } catch(Exception e){
            user = null;
        }

        Assert.assertEquals(null,user);
    }

    @Test
    public void insertTest() throws NoSuchAlgorithmException {
        ud.insert(u);

        User tr = us.getUserById(5);

        Assert.assertEquals(5,tr.getUser_id());
    }

    @Test
    public void getByIdTest() {
        int id = u.getUser_id();
        User user = ud.getById(id);
        String actual = user.getFirstname();
        Assert.assertEquals("some",actual);
    }

    @Test
    public void getByUserIdTest(){
        int id = u.getRole_id();
        List<User> ul = ud.getByUserId(id);
        User user;
        for(User u2: ul){
            if(u2.getUsername().equals("some guy")){
                user = u2;
                String actual = user.getUsername();
                Assert.assertEquals("some guy", actual);
            }else{
                continue;
            }
        }

    }

    @Test
    public void getByUserNameTest(){
        String username = "bob";
        String actual = ud.getByUsername(username).getUsername();
        System.out.println(actual);

        Assert.assertEquals("bob",actual);
    }
}
