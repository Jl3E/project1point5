package com.project1point5.servlet.userServlet;

import com.project1point5.dao.UserDao;
import com.project1point5.model.Reimbursement;
import com.project1point5.model.User;
import com.project1point5.service.ReimbursementService;
import com.project1point5.service.UserService;
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
 * Inserts User into DB using HTTP Post
 *      Reads request body. Json must be formatted like:
 *
 * {
 * "user": {
 * "id": 1,
 * "username": "bob",
 * "password": "bob",
 * "firstname": "bob",
 * "lastname": "bob",
 * "email": "bob@bob.bob",
 * "role_id": 1
 * }
 * }
 */
@WebServlet("/user/insert")
public class UserInsertServlet extends HttpServlet {

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
        User user = null;
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
            user = getUserFromJsonString(jsonBuffer);
            UserService userService = new UserService();
            userService.insert(user);
            out.print("Success");
        }catch(Exception e){
            out.print("Failure");
        }
    }

    /**
     * Uses a json string to build a user object
     * @param jsonBuffer
     * @return
     */
    public User getUserFromJsonString(StringBuilder jsonBuffer){
        JSONObject jsonObject = new JSONObject(jsonBuffer.toString());
        int id = jsonObject.getJSONObject("user").getInt("id");
        String username = jsonObject.getJSONObject("user").getString("username");
        String password = jsonObject.getJSONObject("user").getString("password");
        String firstname = jsonObject.getJSONObject("user").getString("firstname");
        String lastname = jsonObject.getJSONObject("user").getString("lastname");
        String email = jsonObject.getJSONObject("user").getString("email");
        int role_id = jsonObject.getJSONObject("user").getInt("role_id");

        return new User(id, username, password, firstname, lastname, email, role_id);
    }
}
