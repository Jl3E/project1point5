package com.project1point5.servlet.userServlet;


import com.google.gson.GsonBuilder;
import com.project1point5.model.User;
import com.project1point5.service.UserService;
import com.project1point5.util.HideUsernameAndPassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Used to retrieve user by username.
 *      ID can be passed in url ex: http://localhost:8080/0.0.1/user/getByUsername?username=Bob
 */
@WebServlet("/user/getByUsername")
public class UserGetByUsernameServlet extends HttpServlet {

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
        out.print("No use for doPut()");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("No use for doDelete()");
        out.flush();
    }

    public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = "username";
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        String usernameValue = req.getParameter(username);

        //return error if client does not provide a username
        if (usernameValue == null) {
            out.write("Provide a 'username'");
            out.flush();
            return;
        }

        //Call ReimbursementService to call dao and return the object
        UserService userService = new UserService();
        User user = userService.getUserByUsername(usernameValue);

        if(user != null) {
            HideUsernameAndPassword.hideDetails(user);
        }

        //Print json of user to body
        out.print(new GsonBuilder().setPrettyPrinting().create().toJson(user));
        out.flush();
    }
}