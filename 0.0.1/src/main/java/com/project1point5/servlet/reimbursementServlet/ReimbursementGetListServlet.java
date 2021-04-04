package com.project1point5.servlet.reimbursementServlet;

import com.google.gson.GsonBuilder;
import com.project1point5.model.Reimbursement;
import com.project1point5.service.ReimbursementService;
import com.project1point5.util.HideUsernameAndPassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to retrieve all reimbursements
 */

@WebServlet("/reimbursement/getList")
public class ReimbursementGetListServlet extends HttpServlet {

    //Delete later. This is used because my DB is broken
//    Date date = new Date();
//    Timestamp timestamp2 = new Timestamp(date.getTime());
//    Reimbursement reimbursement1 = new Reimbursement(1,50, timestamp2,timestamp2,"something",1,1,1,1);
//    Reimbursement reimbursement2 = new Reimbursement(2,50, timestamp2,timestamp2,"something",1,1,1,1);
//    List<Reimbursement> reimbursements = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("No use for doPost()");
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("No need for doPut()");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("No need for doDelete()");
        out.flush();
    }

    /**
     *      Handles doGet() and doPost()
     * @param req
     * @param resp
     * @throws IOException
     */
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        ReimbursementService reimbursementService = new ReimbursementService();
        List<Reimbursement> reimbursements = reimbursementService.fetchAllReimbursements();

        //Hide username and password
        for(Reimbursement reimbursement : reimbursements){
            HideUsernameAndPassword.hideDetails(reimbursement.getResolver());
        }

        out.print(new GsonBuilder().setPrettyPrinting().create().toJson(reimbursements));

        out.flush();
    }
}
