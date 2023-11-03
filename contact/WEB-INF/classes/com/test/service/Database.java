package com.test.service;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Service;

@Service
public class Database {
    private static Connection c= null;

    public Connection getConnection(){
        c=null;
        try {
            Class.forName("org.postgresql.Driver");
            c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/aakash","postgres","12345");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
}
