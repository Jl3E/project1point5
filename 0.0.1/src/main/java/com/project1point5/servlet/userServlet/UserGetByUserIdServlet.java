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
 * Used to retrieve users by role_id.
 *      ID can be passed in url ex: http://localhost:8080/0.0.1/user/getByUserId?role_id=4
 */
@WebServlet("/user/getByUserId")
public class UserGetByUserIdServlet extends HttpServlet{

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
        String roleId = "role_id";
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        String roleIdValue = req.getParameter(roleId);

        //return error if client does not provide a role id
        if(roleIdValue == null){
            out.write("Provide a 'role_id'");
            out.flush();
            return;
        }

        //check that user role id is an integer
        if(isInteger(roleIdValue)){
            //Call ReimbursementService to call dao and return the object
            UserService userService = new UserService();
            List<User> users = userService.getByUserId(Integer.parseInt(roleIdValue));

            if(users != null) {
                //Hide username and password of users
                for (User user : users) {
                    HideUsernameAndPassword.hideDetails(user);
                }
            }

            //Print json of users to body
            out.print(new GsonBuilder().setPrettyPrinting().create().toJson(users));
        }else{
            out.write("'role_id' must be an integer");
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
