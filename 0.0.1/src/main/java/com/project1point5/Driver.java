package com.project1point5;

import com.project1point5.model.Reimbursement;
import com.project1point5.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.util.Date;

public class Driver {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        User u = new User(1,"bob","bobPass","b","bob","bob@mail.com",1);
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());
        Reimbursement r = new Reimbursement(1,50, timestamp2,timestamp2,"something",1,1,1,1);

        Transaction t = session.beginTransaction();
        session.persist(r);
        session.persist(u);
        session.flush();
        t.commit();
        session.close();


    }
}
