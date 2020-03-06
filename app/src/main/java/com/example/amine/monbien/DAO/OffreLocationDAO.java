package com.example.amine.monbien.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.amine.monbien.Contract.OffreLocationContract;
import com.example.amine.monbien.Helper.OffreLocationHelper;
import com.example.amine.monbien.entities.offredeLocation;
import java.util.ArrayList;
import java.util.List;

public class OffreLocationDAO {
    OffreLocationHelper offreLocationHelper;
    public OffreLocationDAO(Context context) { offreLocationHelper = new OffreLocationHelper(context);
    }

    public long insertOffreLocation(offredeLocation offrelocation){
        SQLiteDatabase db = offreLocationHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OffreLocationContract.OffreLocationEntry.NUMERO_TELPHONE,offrelocation.getNumtel());
        cv.put(OffreLocationContract.OffreLocationEntry.NOM,offrelocation.getNom());
        cv.put(OffreLocationContract.OffreLocationEntry.TYPE_BIEN,offrelocation.getTypebien());
        cv.put(OffreLocationContract.OffreLocationEntry.NOMBRE_CHAMBRE,offrelocation.getNbchambre());
        cv.put(OffreLocationContract.OffreLocationEntry.ETAGE,offrelocation.getNbetage());
        cv.put(OffreLocationContract.OffreLocationEntry.REGION,offrelocation.getRegion());
        cv.put(OffreLocationContract.OffreLocationEntry.VILLE,offrelocation.getVille());
        cv.put(OffreLocationContract.OffreLocationEntry.PRIX,offrelocation.getPrix());
        cv.put(OffreLocationContract.OffreLocationEntry.REMARQUE,offrelocation.getRemarque());
        cv.put(OffreLocationContract.OffreLocationEntry.DATE,offrelocation.getDate());
        cv.put(OffreLocationContract.OffreLocationEntry.NOM_UTILISATEUR,offrelocation.getNomutilisateur());

        return db.insert(OffreLocationContract.OffreLocationEntry.TABLE_NAME,null,cv);
    }
    public List<offredeLocation> GetAllOffreLocation(){
        List<offredeLocation> offerdeLocations = new ArrayList<>();
        SQLiteDatabase db = offreLocationHelper.getReadableDatabase();
        Cursor cursor = db.query(OffreLocationContract.OffreLocationEntry.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            offredeLocation offreLocation = new offredeLocation();
            offreLocation.setNumtel(cursor.getString(1));
            offreLocation.setNom(cursor.getString(2));
            offreLocation.setTypebien(cursor.getString(3));
            offreLocation.setNbchambre(cursor.getString(4));
            offreLocation.setNbetage(cursor.getString(5));
            offreLocation.setRegion(cursor.getString(6));
            offreLocation.setVille(cursor.getString(7));
            offreLocation.setPrix(cursor.getString(8));
            offreLocation.setRemarque(cursor.getString(9));
            offreLocation.setDate(cursor.getString(10));
            offreLocation.setNomutilisateur(cursor.getString(11));
            offerdeLocations.add(offreLocation);
            cursor.moveToNext();
        }
        cursor.close();
        return offerdeLocations;
    }
    public int Delete(){
        SQLiteDatabase db = offreLocationHelper.getWritableDatabase();
        return  db.delete(OffreLocationContract.OffreLocationEntry.TABLE_NAME, "1", null);
    }
}
