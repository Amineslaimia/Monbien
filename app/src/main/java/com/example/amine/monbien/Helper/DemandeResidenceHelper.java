package com.example.amine.monbien.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.amine.monbien.Contract.DemandeResidenceContract;


public class DemandeResidenceHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION =1;
    public static final String DB_NAME ="DemandeResidence.db";
    public static final String COMMA_SEP = ",";
    public static final String TEXT_TYPE =" TEXT";


    public static final String CREATE_DEMANDE_RESIDENCE_TABLE_SQL =
            "CREATE TABLE "+ DemandeResidenceContract.DemandeResidenceEntry.TABLE_NAME +" ("
                    + DemandeResidenceContract.DemandeResidenceEntry._ID+" INTEGER PRIMARY KEY"+COMMA_SEP
                    +DemandeResidenceContract.DemandeResidenceEntry.NUMERO_TELPHONE+TEXT_TYPE+COMMA_SEP
                    +DemandeResidenceContract.DemandeResidenceEntry.NOM+TEXT_TYPE+COMMA_SEP
                    +DemandeResidenceContract.DemandeResidenceEntry.NOM_RESIDENCE+TEXT_TYPE+COMMA_SEP
                    +DemandeResidenceContract.DemandeResidenceEntry.NOMBRE_CHAMBRE+TEXT_TYPE+COMMA_SEP
                    +DemandeResidenceContract.DemandeResidenceEntry.ETAGE+TEXT_TYPE+COMMA_SEP
                    +DemandeResidenceContract.DemandeResidenceEntry.PRIX+TEXT_TYPE+COMMA_SEP
                    +DemandeResidenceContract.DemandeResidenceEntry.REMARQUE+TEXT_TYPE+COMMA_SEP
                    +DemandeResidenceContract.DemandeResidenceEntry.DATE+TEXT_TYPE+COMMA_SEP
                    +DemandeResidenceContract.DemandeResidenceEntry.NOM_UTILISATEUR+TEXT_TYPE+")";

    public DemandeResidenceHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DEMANDE_RESIDENCE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CREATE_DEMANDE_RESIDENCE_TABLE_SQL);
        onCreate(sqLiteDatabase);
    }
}
