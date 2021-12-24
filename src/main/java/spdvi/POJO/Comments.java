/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.POJO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import spdvi.DataAccess;

/**
 *
 * @author bryan
 */
public class Comments {
    DataAccess da;
    int idComent;
    Date dateTime;
    int idUser;
    int idRegistro;
    String user;
    String text;

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

    public void setUser() throws SQLException {
      try (Connection connection = da.getConnection()){
            PreparedStatement selectStatement = 
                    connection.prepareStatement(
                            "SELECT usuari FROM dbo.usuaris WHERE id_registre = ?"
                    );
            selectStatement.setInt(1, idRegistro);
            ResultSet resultSet = selectStatement.executeQuery();
            
            user = resultSet.getString("useri");
      }
        
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIdComent() {
        return idComent;
    }

    public void setIdComent(int idComent) {
        this.idComent = idComent;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }
    
    
    
    public Comments(int id, String text, Date dateTime, int idUser, int idRegistro) {
        this.idComent = id;
        this.text = text;
        this.dateTime = dateTime;
        this.idUser = idUser;
        this.idRegistro = idRegistro;
    }
    
    public Comments(int id, String text, Date dateTime, int idUser, String user, int idRegistro) {
        this.idComent = id;
        this.text = text;
        this.dateTime = dateTime;
        this.idUser = idUser;
        this.user = user;
        this.idRegistro = idRegistro;
    }

    @Override
    public String toString() {
        return "- " + user + " ("  + dateTime + "): " + text + "\n";
    }

    
}


