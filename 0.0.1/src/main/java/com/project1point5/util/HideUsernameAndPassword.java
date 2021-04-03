package com.project1point5.util;

import com.project1point5.model.User;

import java.util.List;

public class HideUsernameAndPassword {

    public static User hideDetails(User u){
        u.setUsername("Hidden");
        u.setPassword("Hidden");
        return u;
    }

}
