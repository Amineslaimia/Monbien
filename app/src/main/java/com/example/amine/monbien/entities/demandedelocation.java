package com.example.amine.monbien.entities;

import java.util.Date;

public class demandedelocation {
    private String id;
    private String Numtel;
    private String Nom;
    private String Typebien;
    private String Nbchambre;
    private String Nbetage;
    private String Region;
    private String Ville;
    private String Prix;
    private String Remarque;
    private String Date;
    private String Nomutilisateur;

    public demandedelocation() {
    }

    public demandedelocation(String id, String numtel, String nom, String typebien, String nbchambre, String nbetage, String region, String ville, String prix, String remarque, String date, String nomutilisateur) {
        this.id = id;
        Numtel = numtel;
        Nom = nom;
        Typebien = typebien;
        Nbchambre = nbchambre;
        Nbetage = nbetage;
        Region = region;
        Ville = ville;
        Prix = prix;
        Remarque = remarque;
        Date = date;
        Nomutilisateur = nomutilisateur;
    }

    public demandedelocation(String id, String numtel) {
        this.id = id;
        Numtel = numtel;
    }

    public demandedelocation(String numtel, String nom, String typebien, String nbchambre, String nbetage, String region, String ville, String prix, String remarque, String date, String nomutilisateur) {
        Numtel = numtel;
        Nom = nom;
        Typebien = typebien;
        Nbchambre = nbchambre;
        Nbetage = nbetage;
        Region = region;
        Ville = ville;
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

    public String getTypebien() {
        return Typebien;
    }

    public void setTypebien(String typebien) {
        Typebien = typebien;
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

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getRemarque() {
        return Remarque;
    }

    public void setRemarque(String remarque) {
        Remarque = remarque;
    }

    public String getPrix() {
        return Prix;
    }

    public void setPrix(String prix) {
        Prix = prix;
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

    @Override
    public String toString() {
        return "demandedelocation{" +
                "id='" + id + '\'' +
                ", Numtel='" + Numtel + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Typebien='" + Typebien + '\'' +
                ", Nbchambre='" + Nbchambre + '\'' +
                ", Nbetage='" + Nbetage + '\'' +
                ", Region='" + Region + '\'' +
                ", Ville='" + Ville + '\'' +
                ", Prix='" + Prix + '\'' +
                ", Remarque='" + Remarque + '\'' +
                ", Date='" + Date + '\'' +
                ", Nomutilisateur='" + Nomutilisateur + '\'' +
                '}';
    }
}
