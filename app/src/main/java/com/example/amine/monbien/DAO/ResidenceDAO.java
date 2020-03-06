package com.example.amine.monbien.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.amine.monbien.Contract.ResidenceContract;
import com.example.amine.monbien.Helper.ResidenceHelper;
import com.example.amine.monbien.entities.residence;

import java.util.ArrayList;
import java.util.List;

public class ResidenceDAO {
    ResidenceHelper residenceHelper;
    public ResidenceDAO(Context context) { residenceHelper = new ResidenceHelper(context);
    }

    public long insertResidence(residence residance){
        SQLiteDatabase db = residenceHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ResidenceContract.ResidenceEntry.NOM,residance.getNom());
        return db.insert(ResidenceContract.ResidenceEntry.TABLE_NAME,null,cv);
    }
    public List<residence> GetAllResidance(){
        List<residence> residences = new ArrayList<>();
        SQLiteDatabase db = residenceHelper.getReadableDatabase();
        Cursor cursor = db.query(ResidenceContract.ResidenceEntry.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            residence residence = new residence();
            residence.setNom(cursor.getString(1));
            residences.add(residence);
            cursor.moveToNext();
        }
        cursor.close();
        return residences;
    }
    public int Delete(){
        SQLiteDatabase db = residenceHelper.getWritableDatabase();
        return  db.delete(ResidenceContract.ResidenceEntry.TABLE_NAME, "1", null);
    }
}
