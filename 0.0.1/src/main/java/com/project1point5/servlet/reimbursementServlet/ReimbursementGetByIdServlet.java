package com.project1point5.servlet.reimbursementServlet;

import com.google.gson.Gson;
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
import java.util.Date;


/**
 * Used to retrieve reimbursement by id.
 *      ID can be passed in url ex: http://localhost:8080/0.0.1/reimbursement/getById?id=4
 *      ID can be passed in body: Need help figuring out how to send id through body
 */
@WebServlet("/reimbursement/getById")
public class ReimbursementGetByIdServlet extends HttpServlet {

    //Delete later. This is used because my DB is broken
//    Date date = new Date();
//    Timestamp timestamp2 = new Timestamp(date.getTime());
//    Reimbursement reimbursement = new Reimbursement(1,50, timestamp2,timestamp2,"something",1,1,1,1);

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

        String primaryID = "id";
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        String primaryIdValue = req.getParameter(primaryID);

        //return error if client does not provide an id
        if(primaryIdValue == null){
            out.write("Provide an 'id'");
            out.flush();
            return;
        }

        //check that reimbursement id is an integer
        if(isInteger(primaryIdValue)){
            //Call ReimbursementService to call dao and return the object
            ReimbursementService reimbursementService = new ReimbursementService();
            Reimbursement reimbursement = reimbursementService.getReimbursementById(Integer.parseInt(primaryIdValue));

            //Print json of reimbursement to body
            out.print(new GsonBuilder().setPrettyPrinting().create().toJson(reimbursement));
            out.flush();
        }else{
            out.write("'id' must be an integer");
            out.flush();
        }
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
