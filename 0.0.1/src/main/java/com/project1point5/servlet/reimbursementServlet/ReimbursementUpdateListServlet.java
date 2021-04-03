package com.project1point5.servlet.reimbursementServlet;

import com.project1point5.dao.UserDao;
import com.project1point5.model.Reimbursement;
import com.project1point5.service.ReimbursementService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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
 * {
 * "updateList": {
 * "resolver_id": 4,
 * "accept":[ 3 ],
 * "deny":[1,2]
 * }
 * }
 */

@WebServlet("/reimbursement/update")
public class ReimbursementUpdateListServlet extends HttpServlet {

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
            getReimbursementFromJsonString(jsonBuffer);
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
    public void getReimbursementFromJsonString(StringBuilder jsonBuffer){
        JSONObject jsonObject = new JSONObject(jsonBuffer.toString());

        JSONArray accept = jsonObject.getJSONObject("updateList").getJSONArray("accept");
        JSONArray deny = jsonObject.getJSONObject("updateList").getJSONArray("deny");
        int id = jsonObject.getJSONObject("updateList").getInt("resolver_id");


        int[][] j;
        int[] acceptArray = new int[accept.length()];
        for (int i = 0; i < accept.length(); ++i) {
            acceptArray[i] = accept.optInt(i);
        }
        int[] denyArray = new int[deny.length()];
        for (int i = 0; i < deny.length(); ++i) {

            denyArray[i] = deny.optInt(i);
        }
        j= new int[][]{acceptArray, denyArray};

        ReimbursementService rs = new ReimbursementService();
        rs.updateReimbursements(j,id);
    }


}
