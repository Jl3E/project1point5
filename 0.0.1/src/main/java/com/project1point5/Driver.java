package com.project1point5;

import com.project1point5.model.Reimbursement;
import com.project1point5.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.util.Date;

public class Driver {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        User u = new User("bob","bobPass","b","bob","bob@mail.com",1);
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());
        Reimbursement r = new Reimbursement(50, timestamp2,null,"something",1,1,1,1);


//        this.amount = amount;
//        this.submitted = submitted;
//        this.resolved = resolved;
//        this.description = description;
//        this.author = author;
//        this.resolver = resolver;
//        this.status_id = status_id;
//        this.type_id = type_id;

    }
}
