/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spdvi;

import POJO.Pictures;
import POJO.Users;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import POJO.Spaces;

/**
 *
 * @author Alumne
 */
public class DataAccess {
    
    public String randomPassword() {
        int length = 9;
        byte[] array = new byte[256];
        new Random().nextBytes(array);
  
        String randomString
            = new String(array, Charset.forName("UTF-8"));
        StringBuffer r = new StringBuffer();
        
        String  AlphaNumericString
            = randomString
                  .replaceAll("[^A-Za-z0-9]", "");
        
        for (int i = 0; i < AlphaNumericString.length(); i++) {
            if (Character.isLetter(AlphaNumericString.charAt(i))
                    && (length > 0) 
                    || Character.isDigit(AlphaNumericString.charAt(i))
                    && (length > 0)) {
                r.append(AlphaNumericString.charAt(i));
                length--;
            }
        }
        return r.toString();
    }
   

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
    
    public ArrayList<Pictures> getImages(Spaces espacio) {
    ArrayList<Pictures> pictures = new ArrayList<>();
        try (Connection connection = getConnection()){
            PreparedStatement selectStatement = 
                    connection.prepareStatement(
                            "SELECT id_imatge, url FROM dbo.imatges join dbo.espai_imatge on (dbo.espai_imatge.fk_id_imatge = dbo.imatges.id_imatge) WHERE fk_registre = ?;"
                    );
            
            selectStatement.setString(1, espacio.getFk_id_registre());
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {                
                Pictures picture = new Pictures(
                        resultSet.getInt("id_imatge"),
                        resultSet.getURL("url")
                );
                pictures.add(picture);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Esta vacio");
        }
        return pictures;
    }
    
    public ArrayList<Spaces> getSpaces() {
        ArrayList<Spaces> spaces = new ArrayList<>();
        try (Connection connection = getConnection()){
            PreparedStatement selectStatement = 
                    connection.prepareStatement(
                            "SELECT * FROM dbo.espai"
                    );
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {                
                Spaces space = new Spaces(
                        resultSet.getString("registre"),
                        resultSet.getString("nom"),
                        resultSet.getString("descripcions"),
                        resultSet.getString("municipi"),
                        resultSet.getString("adreca"),
                        resultSet.getString("email"),
                        resultSet.getString("web"),
                        resultSet.getString("tipus"),
                        resultSet.getString("modalitats"),
                        resultSet.getString("gestor"),
                        resultSet.getString("serveis"),
                        resultSet.getInt("telefon"),
                        resultSet.getBoolean("visible")
                );
                spaces.add(space);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Esta vacio");
        }
    return spaces;
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
    
    
    public int deleteUser(int id_registre) {
        try (Connection connection = getConnection()) {
            PreparedStatement insertStatement = connection.prepareStatement(
            "DELETE FROM dbo.usuaris WHERE id_registre = ?;"
            );

            insertStatement.setInt(1, id_registre);

            int result = insertStatement.executeUpdate();

            if(result == 0) {
                System.out.println("No se ha eliminado nada");
            }
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int deleteSpace(String registre) {
        try (Connection connection = getConnection()) {
            PreparedStatement insertStatement = connection.prepareStatement(
            "DELETE FROM dbo.espai WHERE registre = ?;"
            );

            insertStatement.setString(1, registre);

            int result = insertStatement.executeUpdate();

            if(result == 0) {
                System.out.println("No se ha eliminado nada");
            }
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int actualizarUser(String user, String password, int id_registre) {
        try (Connection connection = getConnection()) {
        PreparedStatement updateStatement = connection.prepareStatement(
        "UPDATE dbo.usuaris set usuari = ?, password = ? WHERE id_registre = ?;"
        );
        
        updateStatement.setString(1, user);
        updateStatement.setString(2, password);
        updateStatement.setInt(3, id_registre);
        
        int result = updateStatement.executeUpdate();
        
        if(result == 0) {
            System.out.println("No se ha eliminado nada");
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    public int newRegistre(){
        try (Connection connection = getConnection()) {
                PreparedStatement selectStatetement = connection.prepareStatement(
                        "SELECT MAX(registre) AS newId FROM dbo.espai"
                );
                ResultSet resultSet = selectStatetement.executeQuery();
                if (!resultSet.next()) {
                    return 0;
                }
                int maxregistre = resultSet.getInt("newId") + 1;
                
                return maxregistre;
        }catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int insertSpace(Spaces newSpace) {
        try (Connection connection = getConnection()) {
            PreparedStatement insertStatement = connection.prepareStatement(
            "INSERT INTO dbo.espai (nom, descripcions, municipi, adreca, email, web, tipus, modalitats, gestor, serveis, telefon, visible, registre)" 
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"
            );
            insertStatement.setString(1, newSpace.getNom());
            insertStatement.setString(2, newSpace.getDescripcions());
            insertStatement.setString(3, newSpace.getMunicipi());
            insertStatement.setString(4, newSpace.getAdreca());
            insertStatement.setString(5, newSpace.getEmail());
            insertStatement.setString(6, newSpace.getWeb());
            insertStatement.setString(7, newSpace.getTipus());
            insertStatement.setString(8, newSpace.getModalitats());
            insertStatement.setString(9, newSpace.getGestor());
            insertStatement.setString(10, newSpace.getServeis());
            insertStatement.setInt(11, newSpace.getTelefon());
            insertStatement.setBoolean(12, newSpace.isVisible());
            insertStatement.setString(13, String.valueOf(newRegistre()));
            
            int result = insertStatement.executeUpdate();
            
            if (result > 0) {
                PreparedStatement selectStatetement = connection.prepareStatement(
                        "SELECT MAX(registre) AS newId FROM dbo.espai"
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
