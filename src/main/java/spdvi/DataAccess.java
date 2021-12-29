/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spdvi;

import spdvi.POJO.Users;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Alumne
 */
public class DataAccess {

    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        
        return sb.toString();
    }
    
    public ImageIcon resizeImageIcon(BufferedImage originalImage, int desiredWidth, int desiredHeight) {
        // Iniciamos las 2 variables de anchura y altura de la imagen nuevas
        int newHeight = 0;
        int newWidth = 0;
        // Hacemos Casting de float para que no de entero y nos de el valor que toca
        float aspectRatio = (float)originalImage.getWidth() / (float)originalImage.getHeight();
        // Comprovamos si la imagen es mas alta o mas ancha.
        if (originalImage.getWidth() > originalImage.getHeight()) {
            // Asignamos los nuevos valores dependiendo de ello y calculamos.
            newWidth = desiredWidth;
            newHeight = Math.round(desiredWidth / aspectRatio);
        } else {
            newHeight = desiredHeight;
            newWidth = Math.round(desiredWidth * aspectRatio) ;
        }
        // Escalamos la imagen
        Image resultingImage = originalImage.getScaledInstance(desiredWidth,desiredHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.SCALE_DEFAULT);
        // Permite dibujar de nuevo la imagen al BufferedImage
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        ImageIcon imageIcon = new ImageIcon(outputImage);
        // Devolvemos la imagen
        return imageIcon;
    }
    
    public Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        try {
            // Get inputStream from the File: dataBaseProperties.properties  
            properties.load(DataAccess.class.getClassLoader().getResourceAsStream("propiedades.properties"));
            // Connection to DataBase with Properties.
            connection = 
                    DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"), 
                    properties.getProperty("password"));
            
        } catch (IOException | SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha conectado");
        }
        return connection;
    }
    
    public ArrayList<Users> getUsers() {
        ArrayList<Users> users = new ArrayList<>();
        try (Connection connection = getConnection()){
            PreparedStatement selectStatement = 
                    connection.prepareStatement(
                            "SELECT * FROM dbo.usuaris"
                    );
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {                
                Users user = new Users(
                        resultSet.getString("usuari"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("admin")
                );
                user.setId_registre(resultSet.getInt("id_registre"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Esta vacio");
        }
    return users;
    }
    
    public int insertUser(Users newUser) {
        try (Connection connection = getConnection()) {
            PreparedStatement insertStatement = connection.prepareStatement(
            "INSERT INTO dbo.usuaris (usuari, email, password, admin)" 
            + "VALUES (?,?,?,?)"
            );
            insertStatement.setString(1, newUser.getUsuari());
            insertStatement.setString(2, newUser.getEmail());
            insertStatement.setString(3, newUser.getPassword());
            insertStatement.setBoolean(4, newUser.isAdmin());
            
            int result = insertStatement.executeUpdate();
            
            if (result > 0) {
                PreparedStatement selectStatetement = connection.prepareStatement(
                        "SELECT MAX(id_registre) AS newId FROM dbo.usuaris"
                );
                ResultSet resultSet = selectStatetement.executeQuery();
                if (!resultSet.next()) {
                    return 0;
                }
                return resultSet.getInt("newId");
            }
            return result;
        }catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
