package com.project1point5.servlet.reimbursementServlet;

import com.project1point5.dao.ReimbursementDao;
import com.project1point5.model.Reimbursement;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Deletes reimbursement from DB using HTTP Delete
 *      Reads request body. Json must be formatted like:
 *
 * {
 * "reimbursement": {
 * "id": 1,
 * }
 * }
 */

@WebServlet("/reimbursement/delete")
public class ReimbursementDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("No use for doGet()");
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("No use for doPut()");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("No use for doPost()");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        delete(req, resp);
    }

    /**
     * Handles doDelete()
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Reimbursement reimbursement;
        PrintWriter out = resp.getWriter();
        StringBuilder jsonBuffer = new StringBuilder();
        String line;

        //Try reading body
        try{
            BufferedReader reader = req.getReader();
            while((line = reader.readLine()) != null){
                jsonBuffer.append(line);
            }
        }catch (Exception e){
            out = resp.getWriter();
            out.write("Couldn't read body");
        }

        //Try to create reimbursement with json body contents
        try{
            reimbursement = getReimbursementFromJsonString(jsonBuffer);
            ReimbursementDao reimbursementDao = new ReimbursementDao();
            reimbursementDao.delete(reimbursement);
            out.print("Success");
        }catch(Exception e){
            out.print("Failure");
        }
    }

    /**
     * Uses a json string to build a reimbursement object
     */
    public Reimbursement getReimbursementFromJsonString(StringBuilder jsonBuffer){
        JSONObject jsonObject = new JSONObject(jsonBuffer.toString());
        int id = jsonObject.getJSONObject("reimbursement").getInt("id");
//        float amount = jsonObject.getJSONObject("reimbursement").getFloat("amount");
//        String s = jsonObject.getJSONObject("reimbursement").getString("submitted");
//        String r = jsonObject.getJSONObject("reimbursement").getString("resolved");
//        String description = jsonObject.getJSONObject("reimbursement").getString("description");
//        int author = jsonObject.getJSONObject("reimbursement").getInt("author");
//        int resolver = jsonObject.getJSONObject("reimbursement").getInt("resolver");
//        int status_id = jsonObject.getJSONObject("reimbursement").getInt("status_id");
//        int type_id = jsonObject.getJSONObject("reimbursement").getInt("type_id");
//        Timestamp submitted = Timestamp.valueOf(s);
//        Timestamp resolved = Timestamp.valueOf(r);
//
//        UserDao userDao = new UserDao();
//        return new Reimbursement(id, amount, submitted, resolved, description, author, userDao.getById(resolver), status_id, type_id);

        ReimbursementDao reimbursementDao = new ReimbursementDao();
        return reimbursementDao.getById(id);
    }

}
