package com.example.amine.monbien.entities;

public class demanderesidence {
    private String id;
    private String Numtel;
    private String Nom;
    private String nomresidence;
    private String Nbchambre;
    private String Nbetage;
    private String Prix;
    private String Remarque;
    private String Date;
    private String Nomutilisateur;


    public demanderesidence() {
    }

    public demanderesidence(String id, String numtel, String nom, String nomresidence, String nbchambre, String nbetage, String prix, String remarque, String date, String nomutilisateur) {
        this.id = id;
        Numtel = numtel;
        Nom = nom;
        this.nomresidence = nomresidence;
        Nbchambre = nbchambre;
        Nbetage = nbetage;
        Prix = prix;
        Remarque = remarque;
        Date = date;
        Nomutilisateur = nomutilisateur;
    }

    public demanderesidence(String numtel, String nom, String nomresidence, String nbchambre, String nbetage, String prix, String remarque, String date, String nomutilisateur) {
        Numtel = numtel;
        Nom = nom;
        this.nomresidence = nomresidence;
        Nbchambre = nbchambre;
        Nbetage = nbetage;
        Prix = prix;
        Remarque = remarque;
        Date = date;
        Nomutilisateur = nomutilisateur;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumtel() {
        return Numtel;
    }

    public void setNumtel(String numtel) {
        Numtel = numtel;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getNomresidence() {
        return nomresidence;
    }

    public void setNomresidence(String nomresidence) {
        this.nomresidence = nomresidence;
    }

    public String getNbchambre() {
        return Nbchambre;
    }

    public void setNbchambre(String nbchambre) {
        Nbchambre = nbchambre;
    }

    public String getNbetage() {
        return Nbetage;
    }

    public void setNbetage(String nbetage) {
        Nbetage = nbetage;
    }

    public String getPrix() {
        return Prix;
    }

    public void setPrix(String prix) {
        Prix = prix;
    }

    public String getRemarque() {
        return Remarque;
    }

    public void setRemarque(String remarque) {
        Remarque = remarque;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNomutilisateur() {
        return Nomutilisateur;
    }

    public void setNomutilisateur(String nomutilisateur) {
        Nomutilisateur = nomutilisateur;
    }
}
