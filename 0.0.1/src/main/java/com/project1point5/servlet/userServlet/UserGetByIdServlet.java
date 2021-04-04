package com.project1point5.servlet.userServlet;

import com.google.gson.GsonBuilder;
import com.project1point5.model.User;
import com.project1point5.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Used to retrieve User by id.
 *      ID can be passed in url ex: http://localhost:8080/0.0.1/user/getById?id=1
 */
@WebServlet("/user/getById")
public class UserGetByIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
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
        PrintWriter out = resp.getWriter();
        out.print("No use for doDelete()");
        out.flush();
    }

    public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {

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

        if(isInteger(primaryIdValue)){
            UserService userService = new UserService();
            User user = userService.getUserById(Integer.parseInt(primaryIdValue));
            out.print(new GsonBuilder().setPrettyPrinting().create().toJson(user));
        }else{
            out.write("'id' must be an integer");
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
