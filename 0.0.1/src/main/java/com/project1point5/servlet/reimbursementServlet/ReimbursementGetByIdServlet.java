package com.project1point5.servlet.reimbursementServlet;

import com.project1point5.service.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Used to retrieve reimbursement by primary id.
 *      ID can be passed in url ex: http://localhost:8080/0.0.1/reimbursement/getById?id=4
 *      ID can be passed in body
 */
@WebServlet("/reimbursement/getById")
public class ReimbursementGetByIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String primaryID = "id";
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        String primaryIdValue = req.getParameter(primaryID);

        //return error if client does not provide an id
        if(primaryIdValue == null){
            out.write("Provide an 'id'");
            return;
        }

        //check that reimbursement id is an integer
        if(isInteger(primaryIdValue)){
            //Call ReimbursementService to call dao and return the object
            ReimbursementService reimbursementService = new ReimbursementService();
            reimbursementService.getReimbursementById(Integer.parseInt(primaryIdValue));
            out.print("Good job");
        }else{
            out.write("'id' must be an integer");
            return;
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
