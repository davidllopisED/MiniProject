/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.POJO;

import java.util.Date;

/**
 *
 * @author bryan
 */
public class Comments {
    
    int id;
    Date dateTime;
    int idUser;
    String user;
    String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    public Comments(int id, String text, Date dateTime,  int idUser) {
        this.id = id;
        this.text = text;
        this.dateTime = dateTime;
        this.idUser = idUser;
    }
    
    public Comments(int id, String text, Date dateTime, int idUser, String user) {
        this.id = id;
        this.text = text;
        this.dateTime = dateTime;
        this.idUser = idUser;
        this.user = user;
    }

    @Override
    public String toString() {
        return "- " + user + " ("  + dateTime + "): " + text + "\n";
    }

    
}


