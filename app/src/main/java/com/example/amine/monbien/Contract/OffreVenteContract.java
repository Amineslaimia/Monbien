package com.example.amine.monbien.Contract;

import android.provider.BaseColumns;

public class OffreVenteContract {
    public static class OffreVenteEntry implements BaseColumns {
        public static final String NUMERO_TELPHONE = "Numtel";
        public static final String NOM = "Nom";
        public static final String TYPE_BIEN = "Typebien";
        public static final String NOMBRE_CHAMBRE = "Nbchambre";
        public static final String ETAGE = "Etage";
        public static final String REGION = "Region";
        public static final String VILLE = "Ville";
        public static final String PRIX = "Prix";
        public static final String REMARQUE = "Remarque";
        public static final String DATE = "Date";
        public static final String NOM_UTILISATEUR = "Nomutilisateur";
        public static final String TABLE_NAME = "OffreVente";

    }
}
