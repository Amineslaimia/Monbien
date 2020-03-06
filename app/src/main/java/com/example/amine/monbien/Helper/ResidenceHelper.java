package com.example.amine.monbien.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.amine.monbien.Contract.ResidenceContract;

public class ResidenceHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION =1;
    public static final String DB_NAME ="Residence.db";
    public static final String COMMA_SEP = ",";
    public static final String TEXT_TYPE =" TEXT";


    public static final String CREATE_RESIDENCE_TABLE_SQL =
            "CREATE TABLE "+ ResidenceContract.ResidenceEntry.TABLE_NAME +" ("
                    + ResidenceContract.ResidenceEntry._ID+" INTEGER PRIMARY KEY"+COMMA_SEP
                    +ResidenceContract.ResidenceEntry.NOM+TEXT_TYPE+")";

    public ResidenceHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_RESIDENCE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CREATE_RESIDENCE_TABLE_SQL);
        onCreate(sqLiteDatabase);
    }
}
