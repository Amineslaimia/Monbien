package com.example.amine.monbien.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.amine.monbien.Contract.DemandeLocationContract;

public class DemandeLocationHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION =1;
    public static final String DB_NAME ="DemandeLocation.db";
    public static final String COMMA_SEP = ",";
    public static final String TEXT_TYPE =" TEXT";


    public static final String CREATE_DEMANDE_LOCATION_TABLE_SQL =
            "CREATE TABLE "+ DemandeLocationContract.DemandeLocationEntry.TABLE_NAME +" ("
                    + DemandeLocationContract.DemandeLocationEntry._ID+" INTEGER PRIMARY KEY"+COMMA_SEP
                    +DemandeLocationContract.DemandeLocationEntry.NUMERO_TELPHONE+TEXT_TYPE+COMMA_SEP
                    +DemandeLocationContract.DemandeLocationEntry.NOM+TEXT_TYPE+COMMA_SEP
                    +DemandeLocationContract.DemandeLocationEntry.TYPE_BIEN+TEXT_TYPE+COMMA_SEP
                    +DemandeLocationContract.DemandeLocationEntry.NOMBRE_CHAMBRE+TEXT_TYPE+COMMA_SEP
                    +DemandeLocationContract.DemandeLocationEntry.ETAGE+TEXT_TYPE+COMMA_SEP
                    +DemandeLocationContract.DemandeLocationEntry.REGION+TEXT_TYPE+COMMA_SEP
                    +DemandeLocationContract.DemandeLocationEntry.VILLE+TEXT_TYPE+COMMA_SEP
                    +DemandeLocationContract.DemandeLocationEntry.PRIX+TEXT_TYPE+COMMA_SEP
                    +DemandeLocationContract.DemandeLocationEntry.REMARQUE+TEXT_TYPE+COMMA_SEP
                    +DemandeLocationContract.DemandeLocationEntry.DATE+TEXT_TYPE+COMMA_SEP
                    +DemandeLocationContract.DemandeLocationEntry.NOM_UTILISATEUR+TEXT_TYPE+")";

    public DemandeLocationHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DEMANDE_LOCATION_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CREATE_DEMANDE_LOCATION_TABLE_SQL);
        onCreate(sqLiteDatabase);
    }
}
