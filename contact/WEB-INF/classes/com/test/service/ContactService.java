package com.test.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.User;

@Service
public class ContactService {
    @Autowired
    private Database database;

    private static Connection c = null;

    public HashMap<Integer,User> displayContact(){
        HashMap<Integer,User> details = new HashMap<>();
        try {
            c= database.getConnection();
            ResultSet rs = c.createStatement().executeQuery("select * from contact order by id ;");

            while (rs.next()) {
                String number = rs.getString("number");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int id = rs.getInt("id");
                details.put(id, new User(name, number, email));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return details;
    }

    public boolean addContact(String name , String number, String email){
        try {
            PreparedStatement ps = database.getConnection().prepareStatement("insert into contact (name,number,email) values (?,?,?);");
            ps.setString(1, name);
            ps.setString(2, number);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            return true;
        }
        return false;
    }
    public void deleteContact(int index){
        try{
            PreparedStatement ps= database.getConnection().prepareStatement("delete from contact where id = ? ;");
            ps.setInt(1, index);
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void editContact(String name ,String number, String mail , int index){
        try {
            PreparedStatement ps =  database.getConnection().prepareStatement( "UPDATE contact SET name = ?,number = ?, email = ? where id = ? ;");
            ps.setString(1, name);
            ps.setString(2, number);
            ps.setString(3, mail);
            ps.setInt(4, index);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
