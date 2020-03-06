package com.example.amine.monbien.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.amine.monbien.Contract.DemandeResidenceContract;
import com.example.amine.monbien.Helper.DemandeResidenceHelper;
import com.example.amine.monbien.entities.demanderesidence;
import java.util.ArrayList;
import java.util.List;

public class DemandeResidenceDAO {
    DemandeResidenceHelper demandeResidenceHelper;
    public DemandeResidenceDAO(Context context) { demandeResidenceHelper = new DemandeResidenceHelper(context);
    }

    public long insertDemandeResidence(demanderesidence demandeResidence){
        SQLiteDatabase db = demandeResidenceHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DemandeResidenceContract.DemandeResidenceEntry.NUMERO_TELPHONE,demandeResidence.getNumtel());
        cv.put(DemandeResidenceContract.DemandeResidenceEntry.NOM,demandeResidence.getNom());
        cv.put(DemandeResidenceContract.DemandeResidenceEntry.NOM_RESIDENCE,demandeResidence.getNomresidence());
        cv.put(DemandeResidenceContract.DemandeResidenceEntry.NOMBRE_CHAMBRE,demandeResidence.getNbchambre());
        cv.put(DemandeResidenceContract.DemandeResidenceEntry.ETAGE,demandeResidence.getNbetage());
        cv.put(DemandeResidenceContract.DemandeResidenceEntry.PRIX,demandeResidence.getPrix());
        cv.put(DemandeResidenceContract.DemandeResidenceEntry.REMARQUE,demandeResidence.getRemarque());
        cv.put(DemandeResidenceContract.DemandeResidenceEntry.DATE,demandeResidence.getDate());
        cv.put(DemandeResidenceContract.DemandeResidenceEntry.NOM_UTILISATEUR,demandeResidence.getNomutilisateur());

        return db.insert(DemandeResidenceContract.DemandeResidenceEntry.TABLE_NAME,null,cv);
    }
    public List<demanderesidence> GetAllDemandeResidence(){
        List<demanderesidence> demanderesidences = new ArrayList<>();
        SQLiteDatabase db = demandeResidenceHelper.getReadableDatabase();
        Cursor cursor = db.query(DemandeResidenceContract.DemandeResidenceEntry.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            demanderesidence demanderesi = new demanderesidence();
            demanderesi.setNumtel(cursor.getString(1));
            demanderesi.setNom(cursor.getString(2));
            demanderesi.setNomresidence(cursor.getString(3));
            demanderesi.setNbchambre(cursor.getString(4));
            demanderesi.setNbetage(cursor.getString(5));
            demanderesi.setPrix(cursor.getString(6));
            demanderesi.setRemarque(cursor.getString(7));
            demanderesi.setDate(cursor.getString(8));
            demanderesi.setNomutilisateur(cursor.getString(9));
            demanderesidences.add(demanderesi);
            cursor.moveToNext();
        }
        cursor.close();
        return demanderesidences;
    }
    public int Delete(){
        SQLiteDatabase db = demandeResidenceHelper.getWritableDatabase();
        return  db.delete(DemandeResidenceContract.DemandeResidenceEntry.TABLE_NAME, "1", null);
    }

}
