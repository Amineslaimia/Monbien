package com.example.amine.monbien.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.amine.monbien.Contract.OffreVenteContract;

public class OffreVenteHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION =1;
    public static final String DB_NAME ="OffreVente.db";
    public static final String COMMA_SEP = ",";
    public static final String TEXT_TYPE =" TEXT";


    public static final String CREATE_OFFRE_VENTE_TABLE_SQL =
            "CREATE TABLE "+ OffreVenteContract.OffreVenteEntry.TABLE_NAME +" ("
                    + OffreVenteContract.OffreVenteEntry._ID+" INTEGER PRIMARY KEY"+COMMA_SEP
                    +OffreVenteContract.OffreVenteEntry.NUMERO_TELPHONE+TEXT_TYPE+COMMA_SEP
                    +OffreVenteContract.OffreVenteEntry.NOM+TEXT_TYPE+COMMA_SEP
                    +OffreVenteContract.OffreVenteEntry.TYPE_BIEN+TEXT_TYPE+COMMA_SEP
                    +OffreVenteContract.OffreVenteEntry.NOMBRE_CHAMBRE+TEXT_TYPE+COMMA_SEP
                    +OffreVenteContract.OffreVenteEntry.ETAGE+TEXT_TYPE+COMMA_SEP
                    +OffreVenteContract.OffreVenteEntry.REGION+TEXT_TYPE+COMMA_SEP
                    +OffreVenteContract.OffreVenteEntry.VILLE+TEXT_TYPE+COMMA_SEP
                    +OffreVenteContract.OffreVenteEntry.PRIX+TEXT_TYPE+COMMA_SEP
                    +OffreVenteContract.OffreVenteEntry.REMARQUE+TEXT_TYPE+COMMA_SEP
                    +OffreVenteContract.OffreVenteEntry.DATE+TEXT_TYPE+COMMA_SEP
                    +OffreVenteContract.OffreVenteEntry.NOM_UTILISATEUR+TEXT_TYPE+")";

    public OffreVenteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_OFFRE_VENTE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CREATE_OFFRE_VENTE_TABLE_SQL);
        onCreate(sqLiteDatabase);
    }
}
