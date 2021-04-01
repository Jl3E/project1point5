package com.project1point5;

import com.project1point5.dao.ReimbursementDao;
import com.project1point5.dao.UserDao;
import com.project1point5.model.Reimbursement;
import com.project1point5.model.User;
import com.project1point5.service.ReimbursementService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Driver {

    public static void main(String[] args) {
//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        Session session = factory.openSession();

        User u = new User(1,"bob","bobPass","b","bob","bob@mail.com",1);
        User u1 = new User(2,"jill","jillPass","jill","hands","jill@mail.com",3);
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());
        Reimbursement r = new Reimbursement(1,50, timestamp2,timestamp2,"something",1,1,1,1);
        Reimbursement r2 = new Reimbursement(2,50, timestamp2,timestamp2,"something",2,1,1,1);
        Reimbursement r3 = new Reimbursement(3,518911, timestamp2,timestamp2,"the dudes rich",15,1,1,1);

        ReimbursementDao rd = new ReimbursementDao();

//        rd.insert(r);//works for inserting
        rd.insert(r2);
//        rd.insert(r3);

//        List<Reimbursement> name = rd.getList(); // gets a list of all reimbursements
//        System.out.println(name.get(0).getAmount()); //prove of concept

//        System.out.println(rd.getById(1)); // this gets by id and you can specify a specific value as well.
//        System.out.println(rd.getByUserId(15));// gets the user by the author id
//        rd.delete(r3);
//--------------------------------------------UserDao-------------------------------------------------------------------
        UserDao us = new UserDao();
//        us.insert(u);
//        us.insert(u1);
//        List<User> list = us.getList();
//        System.out.println(list.get(0).getUsername());
//        System.out.println(us.getByUserId(1));
//        System.out.println(us.getById(3));
//        System.out.println(us.getByUsername("bob"));
//        us.delete(u1);
    }
}
