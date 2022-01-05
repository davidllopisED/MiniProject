/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author Alumne
 */
public class Spaces {
    private String fk_id_registre, nom, descripcions, municipi, adreca, email, web, tipus, modalitats, gestor, serveis;
    private int telefon;

    public Spaces() {
    }

    public Spaces(String fk_id_registre, String nom, String descripcions, String municipi, String adreca, String email, String web, String tipus, String modalitats, String gestor, String serveis, int telefon) {
        this.fk_id_registre = fk_id_registre;
        this.nom = nom;
        this.descripcions = descripcions;
        this.municipi = municipi;
        this.adreca = adreca;
        this.email = email;
        this.web = web;
        this.tipus = tipus;
        this.modalitats = modalitats;
        this.gestor = gestor;
        this.serveis = serveis;
        this.telefon = telefon;
    }

    public String getFk_id_registre() {
        return fk_id_registre;
    }

    public void setFk_id_registre(String fk_id_registre) {
        this.fk_id_registre = fk_id_registre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcions() {
        return descripcions;
    }

    public void setDescripcions(String descripcions) {
        this.descripcions = descripcions;
    }

    public String getMunicipi() {
        return municipi;
    }

    public void setMunicipi(String municipi) {
        this.municipi = municipi;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getModalitats() {
        return modalitats;
    }

    public void setModalitats(String modalitats) {
        this.modalitats = modalitats;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public String getServeis() {
        return serveis;
    }

    public void setServeis(String serveis) {
        this.serveis = serveis;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return fk_id_registre + ": \"" + nom + "\". " + municipi + ", " + adreca;
    }
}
