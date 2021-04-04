package com.project1point5.servlet.userServlet;

import com.project1point5.model.User;
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

@WebServlet("/user/delete")
public class UserDeleteServlet extends HttpServlet {

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
     * @param req
     * @param resp
     * @throws IOException
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
            userService.delete(user);
            out.print("Success");
        }catch(Exception e){
            out.print("Failure");
        }
    }

    public User getUserFromJsonString(StringBuilder jsonBuffer){
        JSONObject jsonObject = new JSONObject(jsonBuffer.toString());
        int id = jsonObject.getJSONObject("user").getInt("id");
        String username = jsonObject.getJSONObject("user").getString("username");
        String password = jsonObject.getJSONObject("user").getString("password");

        UserService userService = new UserService();

        //NEED TO IMPLEMENT GETUSERBYLOGIN
//        return userService.getUserByLogin(username, password);

        return userService.getUserById(id);
    }
}
