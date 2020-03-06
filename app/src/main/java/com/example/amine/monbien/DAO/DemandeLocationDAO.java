package com.example.amine.monbien.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.amine.monbien.Contract.DemandeAchatContract;
import com.example.amine.monbien.Contract.DemandeLocationContract;
import com.example.amine.monbien.Helper.DemandeLocationHelper;
import com.example.amine.monbien.entities.demandedelocation;

import java.util.ArrayList;
import java.util.List;

public class DemandeLocationDAO {
    DemandeLocationHelper demandeLocationHelper;
    public DemandeLocationDAO(Context context) { demandeLocationHelper = new DemandeLocationHelper(context);
    }

    public long insertDemandeLocation(demandedelocation demandelocation){
        SQLiteDatabase db = demandeLocationHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DemandeLocationContract.DemandeLocationEntry.NUMERO_TELPHONE,demandelocation.getNumtel());
        cv.put(DemandeLocationContract.DemandeLocationEntry.NOM,demandelocation.getNom());
        cv.put(DemandeLocationContract.DemandeLocationEntry.TYPE_BIEN,demandelocation.getTypebien());
        cv.put(DemandeLocationContract.DemandeLocationEntry.NOMBRE_CHAMBRE,demandelocation.getNbchambre());
        cv.put(DemandeLocationContract.DemandeLocationEntry.ETAGE,demandelocation.getNbetage());
        cv.put(DemandeLocationContract.DemandeLocationEntry.REGION,demandelocation.getRegion());
        cv.put(DemandeLocationContract.DemandeLocationEntry.VILLE,demandelocation.getVille());
        cv.put(DemandeLocationContract.DemandeLocationEntry.PRIX,demandelocation.getPrix());
        cv.put(DemandeLocationContract.DemandeLocationEntry.REMARQUE,demandelocation.getRemarque());
        cv.put(DemandeLocationContract.DemandeLocationEntry.DATE,demandelocation.getDate());
        cv.put(DemandeLocationContract.DemandeLocationEntry.NOM_UTILISATEUR,demandelocation.getNomutilisateur());

        return db.insert(DemandeLocationContract.DemandeLocationEntry.TABLE_NAME,null,cv);
    }
    public List<demandedelocation> GetAllDemandeLocation(){
        List<demandedelocation> demandeLocations = new ArrayList<>();
        SQLiteDatabase db = demandeLocationHelper.getReadableDatabase();
        Cursor cursor = db.query(DemandeLocationContract.DemandeLocationEntry.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            demandedelocation demandeLocation = new demandedelocation();
            demandeLocation.setNumtel(cursor.getString(1));
            demandeLocation.setNom(cursor.getString(2));
            demandeLocation.setTypebien(cursor.getString(3));
            demandeLocation.setNbchambre(cursor.getString(4));
            demandeLocation.setNbetage(cursor.getString(5));
            demandeLocation.setRegion(cursor.getString(6));
            demandeLocation.setVille(cursor.getString(7));
            demandeLocation.setPrix(cursor.getString(8));
            demandeLocation.setRemarque(cursor.getString(9));
            demandeLocation.setDate(cursor.getString(10));
            demandeLocation.setNomutilisateur(cursor.getString(11));
            demandeLocations.add(demandeLocation);
            cursor.moveToNext();
        }
        cursor.close();
        return demandeLocations;
    }
    public int Delete(){
        SQLiteDatabase db = demandeLocationHelper.getWritableDatabase();
        return  db.delete(DemandeLocationContract.DemandeLocationEntry.TABLE_NAME, "1", null);
    }
}
