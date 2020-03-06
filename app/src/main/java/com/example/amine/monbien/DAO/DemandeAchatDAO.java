package com.example.amine.monbien.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.amine.monbien.Contract.DemandeAchatContract;
import com.example.amine.monbien.Helper.DemandeAchatHelper;
import com.example.amine.monbien.entities.demandeachat;
import java.util.ArrayList;
import java.util.List;

public class DemandeAchatDAO {
    DemandeAchatHelper demandeAchatHelper;
    public DemandeAchatDAO(Context context) { demandeAchatHelper = new DemandeAchatHelper(context);
    }

    public long insertDemandeAchat(demandeachat demandeAchat){
        SQLiteDatabase db = demandeAchatHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DemandeAchatContract.DemandeAchatEntry.NUMERO_TELPHONE,demandeAchat.getNumtel());
        cv.put(DemandeAchatContract.DemandeAchatEntry.NOM,demandeAchat.getNom());
        cv.put(DemandeAchatContract.DemandeAchatEntry.TYPE_BIEN,demandeAchat.getTypebien());
        cv.put(DemandeAchatContract.DemandeAchatEntry.NOMBRE_CHAMBRE,demandeAchat.getNbchambre());
        cv.put(DemandeAchatContract.DemandeAchatEntry.ETAGE,demandeAchat.getNbetage());
        cv.put(DemandeAchatContract.DemandeAchatEntry.REGION,demandeAchat.getRegion());
        cv.put(DemandeAchatContract.DemandeAchatEntry.VILLE,demandeAchat.getVille());
        cv.put(DemandeAchatContract.DemandeAchatEntry.PRIX,demandeAchat.getPrix());
        cv.put(DemandeAchatContract.DemandeAchatEntry.REMARQUE,demandeAchat.getRemarque());
        cv.put(DemandeAchatContract.DemandeAchatEntry.DATE,demandeAchat.getDate());
        cv.put(DemandeAchatContract.DemandeAchatEntry.NOM_UTILISATEUR,demandeAchat.getNomutilisateur());

        return db.insert(DemandeAchatContract.DemandeAchatEntry.TABLE_NAME,null,cv);
    }
    public List<demandeachat> GetAllDemandeAchat(){
        List<demandeachat> demandeAchats = new ArrayList<>();
        SQLiteDatabase db = demandeAchatHelper.getReadableDatabase();
        Cursor cursor = db.query(DemandeAchatContract.DemandeAchatEntry.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            demandeachat demandeAchat = new demandeachat();
            demandeAchat.setNumtel(cursor.getString(1));
            demandeAchat.setNom(cursor.getString(2));
            demandeAchat.setTypebien(cursor.getString(3));
            demandeAchat.setNbchambre(cursor.getString(4));
            demandeAchat.setNbetage(cursor.getString(5));
            demandeAchat.setRegion(cursor.getString(6));
            demandeAchat.setVille(cursor.getString(7));
            demandeAchat.setPrix(cursor.getString(8));
            demandeAchat.setRemarque(cursor.getString(9));
            demandeAchat.setDate(cursor.getString(10));
            demandeAchat.setNomutilisateur(cursor.getString(11));
            demandeAchats.add(demandeAchat);
            cursor.moveToNext();
        }
        cursor.close();
        return demandeAchats;
    }
    public int Delete(){
        SQLiteDatabase db = demandeAchatHelper.getWritableDatabase();
        return  db.delete(DemandeAchatContract.DemandeAchatEntry.TABLE_NAME, "1", null);
    }
}
