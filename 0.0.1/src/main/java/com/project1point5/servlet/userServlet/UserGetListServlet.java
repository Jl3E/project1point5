package com.project1point5.servlet.userServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project1point5.dao.UserDao;
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

@WebServlet("/user/getList")
public class UserGetListServlet extends HttpServlet {

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
        PrintWriter out = resp.getWriter();
        UserService userService = new UserService();

        //Change username and password for security purposes
        List<User> users = userService.fetchAllUsers();
        for(User user : users){
            HideUsernameAndPassword.hideDetails(user);
        }

        out.print(new GsonBuilder().setPrettyPrinting().create().toJson(users));
        out.flush();
    }
}
