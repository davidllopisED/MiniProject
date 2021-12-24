/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spdvi.POJO;

/**
 *
 * @author Alumne
 */
public class Users {

    private int id_registre;
    private String usuari;
    private String email;
    private String password;
    private boolean admin;

    public Users(String usuari, String email, String password, boolean admin) {
        this.usuari = usuari;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }
    
    public Users() {
    }

    public int getId_registre() {
        return id_registre;
    }

    public void setId_registre(int id_registre) {
        this.id_registre = id_registre;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Users{" + "id_registre=" + id_registre + ", usuari=" + usuari + ", email=" + email + ", password=" + password + ", admin=" + admin + '}';
    }
}
