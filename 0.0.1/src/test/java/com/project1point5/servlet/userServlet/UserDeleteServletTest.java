package com.project1point5.servlet.userServlet;

import com.project1point5.model.User;
import com.project1point5.service.UserService;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

public class UserDeleteServletTest {
    UserService us = new UserService();
    User u1 = new User(50,"joe","joePass","joe","joe","joe@gmailcom",3);//make sure this is in db
    User u = new User(50,"joe","75736229faba7b9a0dc8a93f78dfd1cc","joe","joe","joe@gmailcom",3);

    @Test
    public void deleteTest() throws IOException, NoSuchAlgorithmException {
        us.insert(u1);
        us.delete(u1);
        User u2 = us.getUserById(50);
        Assert.assertEquals(null,u2);
    }

    @Test
    public void getUserFromJsonStringTest(){
        String jsonBuffer = "{\"user\": {\"id\": 50,\"username\": \"joe\",\"password\": \"joePass\"}}";
        JSONObject jsonObject = new JSONObject(jsonBuffer);
        int id = jsonObject.getJSONObject("user").getInt("id");
        String username = jsonObject.getJSONObject("user").getString("username");
        String password = jsonObject.getJSONObject("user").getString("password");

        UserService userService = new UserService();

        Assert.assertEquals(u,userService.getUserById(id));
    }
}
