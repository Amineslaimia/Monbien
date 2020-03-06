package com.example.amine.monbien.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.amine.monbien.Contract.OffreLocationContract;

public class OffreLocationHelper  extends SQLiteOpenHelper {
    public static final int DB_VERSION =1;
    public static final String DB_NAME ="OffreLocation.db";
    public static final String COMMA_SEP = ",";
    public static final String TEXT_TYPE =" TEXT";


    public static final String CREATE_OFFRE_LOCATION_TABLE_SQL =
            "CREATE TABLE "+ OffreLocationContract.OffreLocationEntry.TABLE_NAME +" ("
                    + OffreLocationContract.OffreLocationEntry._ID+" INTEGER PRIMARY KEY"+COMMA_SEP
                    +OffreLocationContract.OffreLocationEntry.NUMERO_TELPHONE+TEXT_TYPE+COMMA_SEP
                    +OffreLocationContract.OffreLocationEntry.NOM+TEXT_TYPE+COMMA_SEP
                    +OffreLocationContract.OffreLocationEntry.TYPE_BIEN+TEXT_TYPE+COMMA_SEP
                    +OffreLocationContract.OffreLocationEntry.NOMBRE_CHAMBRE+TEXT_TYPE+COMMA_SEP
                    +OffreLocationContract.OffreLocationEntry.ETAGE+TEXT_TYPE+COMMA_SEP
                    +OffreLocationContract.OffreLocationEntry.REGION+TEXT_TYPE+COMMA_SEP
                    +OffreLocationContract.OffreLocationEntry.VILLE+TEXT_TYPE+COMMA_SEP
                    +OffreLocationContract.OffreLocationEntry.PRIX+TEXT_TYPE+COMMA_SEP
                    +OffreLocationContract.OffreLocationEntry.REMARQUE+TEXT_TYPE+COMMA_SEP
                    +OffreLocationContract.OffreLocationEntry.DATE+TEXT_TYPE+COMMA_SEP
                    +OffreLocationContract.OffreLocationEntry.NOM_UTILISATEUR+TEXT_TYPE+")";

    public OffreLocationHelper (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_OFFRE_LOCATION_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CREATE_OFFRE_LOCATION_TABLE_SQL);
        onCreate(sqLiteDatabase);
    }
}
