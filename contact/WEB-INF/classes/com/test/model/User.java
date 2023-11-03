package com.test.model;

public class User {
    String name;
    String mail;
    String number;
    public User(String name ,String number, String mail){
        this.name = name ;
        this.number = number;
        this.mail = mail;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    
}
