package com.example.pierrepapierciseaux.data;

import java.io.Serializable;

public class Utilisateur implements Serializable, Comparable<Utilisateur> {

    private String nom;
    private String prenom;
    private String dateNaiss;
    private String sexe;
    private String mail;
    private int score;

    public Utilisateur() {

    }

    public Utilisateur(String nom, String prenom, String dateNaiss, String sexe, String mail, int score) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.sexe = sexe;
        this.mail = mail;
        this.score = score;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Utilisateur utilisateur) {
        return this.score - utilisateur.getScore();
    }
}
