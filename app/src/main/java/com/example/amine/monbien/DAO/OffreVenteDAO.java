package com.example.amine.monbien.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.amine.monbien.Contract.OffreLocationContract;
import com.example.amine.monbien.Contract.OffreVenteContract;
import com.example.amine.monbien.Helper.OffreVenteHelper;
import com.example.amine.monbien.entities.offredeLocation;
import com.example.amine.monbien.entities.offredevente;

import java.util.ArrayList;
import java.util.List;

public class OffreVenteDAO {
    OffreVenteHelper offreVenteHelper;
    public OffreVenteDAO(Context context) { offreVenteHelper = new OffreVenteHelper(context);
    }

    public long insertOffreVente(offredevente offrevente){
        SQLiteDatabase db = offreVenteHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OffreVenteContract.OffreVenteEntry.NUMERO_TELPHONE,offrevente.getNumtel());
        cv.put(OffreVenteContract.OffreVenteEntry.NOM,offrevente.getNom());
        cv.put(OffreVenteContract.OffreVenteEntry.TYPE_BIEN,offrevente.getTypebien());
        cv.put(OffreVenteContract.OffreVenteEntry.NOMBRE_CHAMBRE,offrevente.getNbchambre());
        cv.put(OffreVenteContract.OffreVenteEntry.ETAGE,offrevente.getNbetage());
        cv.put(OffreVenteContract.OffreVenteEntry.REGION,offrevente.getRegion());
        cv.put(OffreVenteContract.OffreVenteEntry.VILLE,offrevente.getVille());
        cv.put(OffreVenteContract.OffreVenteEntry.PRIX,offrevente.getPrix());
        cv.put(OffreVenteContract.OffreVenteEntry.REMARQUE,offrevente.getRemarque());
        cv.put(OffreVenteContract.OffreVenteEntry.DATE,offrevente.getDate());
        cv.put(OffreVenteContract.OffreVenteEntry.NOM_UTILISATEUR,offrevente.getNomutilisateur());

        return db.insert(OffreVenteContract.OffreVenteEntry.TABLE_NAME,null,cv);
    }
    public List<offredevente> GetAllOffreVente(){
        List<offredevente> offerdeventes = new ArrayList<>();
        SQLiteDatabase db = offreVenteHelper.getReadableDatabase();
        Cursor cursor = db.query(OffreVenteContract.OffreVenteEntry.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            offredevente offrevente = new offredevente();
            offrevente.setNumtel(cursor.getString(1));
            offrevente.setNom(cursor.getString(2));
            offrevente.setTypebien(cursor.getString(3));
            offrevente.setNbchambre(cursor.getString(4));
            offrevente.setNbetage(cursor.getString(5));
            offrevente.setRegion(cursor.getString(6));
            offrevente.setVille(cursor.getString(7));
            offrevente.setPrix(cursor.getString(8));
            offrevente.setRemarque(cursor.getString(9));
            offrevente.setDate(cursor.getString(10));
            offrevente.setNomutilisateur(cursor.getString(11));
            offerdeventes.add(offrevente);
            cursor.moveToNext();
        }
        cursor.close();
        return offerdeventes;
    }
    public int Delete(){
        SQLiteDatabase db = offreVenteHelper.getWritableDatabase();
        return  db.delete(OffreVenteContract.OffreVenteEntry.TABLE_NAME, "1", null);
    }
}
