package com.example.amine.monbien.entities;


    public class residence {
        String id;
        String nom;

        public residence() {
        }

        public residence(String id, String nom) {
            this.id = id;
            this.nom = nom;
        }


        public residence(String nom) {
            this.nom = nom;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        @Override
        public String toString() {
            return "ResidenceL{" +
                    "id='" + id + '\'' +
                    ", nom='" + nom + '\'' +
                    '}';
        }
    }

