package com.project1point5.servlet.reimbursementServlet;

import com.project1point5.dao.UserDao;
import com.project1point5.model.Reimbursement;
import com.project1point5.service.ReimbursementService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;


/**
 * Inserts reimbursement into DB using HTTP Post
 *      Reads request body. Json must be formatted like:
 *
 * {
 * "reimbursement": {
 * "id": 1,
 * "amount": 50,
 * "submitted": "2021-04-02 17:56:11.739",
 * "resolved": "2021-04-02 17:56:11.739",
 * "description": "something",
 * "author": 1,
 * "resolver": 1,
 * "status_id": 1,
 * "type_id": 1
 * }
 * }
 */
@WebServlet("/reimbursement/insert")
public class ReimbursementInsertServlet extends HttpServlet {

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
        insert(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("No use for doDelete()");
        out.flush();
    }

    /**
     * Handles doPost()
     * @param req
     * @param resp
     * @throws IOException
     */
    public void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Reimbursement reimbursement = null;
        PrintWriter out = resp.getWriter();
        StringBuilder jsonBuffer = new StringBuilder();
        String line = null;

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
            ReimbursementService reimbursementService = new ReimbursementService();
            reimbursementService.createReimbursement(reimbursement);
            out.print("Success");
        }catch(Exception e){
            out.print("Failure");
        }
    }

    /**
     * Uses a json string to build a reimbursement object
     * @param jsonBuffer
     * @return
     */
    public Reimbursement getReimbursementFromJsonString(StringBuilder jsonBuffer){
        JSONObject jsonObject = new JSONObject(jsonBuffer.toString());
        int id = jsonObject.getJSONObject("reimbursement").getInt("id");
        float amount = jsonObject.getJSONObject("reimbursement").getFloat("amount");
        String s = jsonObject.getJSONObject("reimbursement").getString("submitted");
        String r = jsonObject.getJSONObject("reimbursement").getString("resolved");
        String description = jsonObject.getJSONObject("reimbursement").getString("description");
        int author = jsonObject.getJSONObject("reimbursement").getInt("author");
        int resolver = jsonObject.getJSONObject("reimbursement").getInt("resolver");
        int status_id = jsonObject.getJSONObject("reimbursement").getInt("status_id");
        int type_id = jsonObject.getJSONObject("reimbursement").getInt("type_id");
        Timestamp submitted = Timestamp.valueOf(s);
        Timestamp resolved = Timestamp.valueOf(r);

        UserDao userDao = new UserDao();
        userDao.getById(resolver);

//        int[][] i, int resolver

        return new Reimbursement(id, amount, submitted, resolved, description, author, userDao.getById(resolver), status_id, type_id);
    }
}
