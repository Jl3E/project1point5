package com.project1point5.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Deprecated
public class ConnectionUtil {

    private static ConnectionUtil instance;

    private ConnectionUtil(){}

    public static ConnectionUtil getInstance(){
        if(instance == null){
            instance = new ConnectionUtil();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://samplepsql.cd1hrpx24rhn.us-west-1.rds.amazonaws.com:5432/postgres?currentSchema=project1",
                "joe",
                "momma");
    }
}
