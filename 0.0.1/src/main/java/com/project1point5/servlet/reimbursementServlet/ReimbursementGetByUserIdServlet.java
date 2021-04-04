package com.project1point5.servlet.reimbursementServlet;

import com.google.gson.GsonBuilder;
import com.project1point5.model.Reimbursement;
import com.project1point5.service.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Used to retrieve reimbursement by author.
 *      ID can be passed in url ex: http://localhost:8080/0.0.1/reimbursement/getByUserId?author=4
 */
@WebServlet("/reimbursement/getByUserId")
public class ReimbursementGetByUserIdServlet extends HttpServlet {

    //Delete later. This is used because my DB is broken
//    Date date = new Date();
//    Timestamp timestamp2 = new Timestamp(date.getTime());
//    Reimbursement reimbursement1 = new Reimbursement(1,50, timestamp2,timestamp2,"something",1,1,1,1);
//    Reimbursement reimbursement2 = new Reimbursement(2,50, timestamp2,timestamp2,"something",1,1,1,1);
//    List<Reimbursement> reimbursements = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
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
        out.print("No use for doPut()");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("No use for doDelete()");
        out.flush();
    }

    /**
     *  Handles doGet() and doPost()
     * @param req
     * @param resp
     * @throws IOException
     */
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String author = "author";
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        String authorValue = req.getParameter(author);

        //return error if client does not provide an author
        if(authorValue == null){
            out.write("Provide an 'author'");
            out.flush();
            return;
        }

        //check that reimbursement author is an integer
        if(isInteger(authorValue)){
            //Call ReimbursementService to call dao and return the object
            ReimbursementService reimbursementService = new ReimbursementService();
            List<Reimbursement> reimbursements = reimbursementService.getReimbursementsByUserID(Integer.parseInt(authorValue));

            //FOR JOSH'S REFERENCE. DELETE LATER
//            reimbursements.add(reimbursement1);
//            reimbursements.add(reimbursement2);

            //Print json of reimbursement to body
            out.print(new GsonBuilder().setPrettyPrinting().create().toJson(reimbursements));
        }else{
            out.write("'author' must be an integer");
        }
        out.flush();
    }

    /**
     *  Check that parameter is an integer
     * @return
     */
    public boolean isInteger(String id){
        try{
            Integer.parseInt(id);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}
