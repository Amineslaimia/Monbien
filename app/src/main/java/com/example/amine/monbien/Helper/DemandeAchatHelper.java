package com.example.amine.monbien.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.amine.monbien.Contract.DemandeAchatContract;

public class DemandeAchatHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION =1;
    public static final String DB_NAME ="DemandeAchat.db";
    public static final String COMMA_SEP = ",";
    public static final String TEXT_TYPE =" TEXT";


    public static final String CREATE_DEMANDE_ACHAT_TABLE_SQL =
            "CREATE TABLE "+ DemandeAchatContract.DemandeAchatEntry.TABLE_NAME +" ("
                    + DemandeAchatContract.DemandeAchatEntry._ID+" INTEGER PRIMARY KEY"+COMMA_SEP
                    +DemandeAchatContract.DemandeAchatEntry.NUMERO_TELPHONE+TEXT_TYPE+COMMA_SEP
                    +DemandeAchatContract.DemandeAchatEntry.NOM+TEXT_TYPE+COMMA_SEP
                    +DemandeAchatContract.DemandeAchatEntry.TYPE_BIEN+TEXT_TYPE+COMMA_SEP
                    +DemandeAchatContract.DemandeAchatEntry.NOMBRE_CHAMBRE+TEXT_TYPE+COMMA_SEP
                    +DemandeAchatContract.DemandeAchatEntry.ETAGE+TEXT_TYPE+COMMA_SEP
                    +DemandeAchatContract.DemandeAchatEntry.REGION+TEXT_TYPE+COMMA_SEP
                    +DemandeAchatContract.DemandeAchatEntry.VILLE+TEXT_TYPE+COMMA_SEP
                    +DemandeAchatContract.DemandeAchatEntry.PRIX+TEXT_TYPE+COMMA_SEP
                    +DemandeAchatContract.DemandeAchatEntry.REMARQUE+TEXT_TYPE+COMMA_SEP
                    +DemandeAchatContract.DemandeAchatEntry.DATE+TEXT_TYPE+COMMA_SEP
                    +DemandeAchatContract.DemandeAchatEntry.NOM_UTILISATEUR+TEXT_TYPE+")";

    public DemandeAchatHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DEMANDE_ACHAT_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CREATE_DEMANDE_ACHAT_TABLE_SQL);
        onCreate(sqLiteDatabase);
    }
}
