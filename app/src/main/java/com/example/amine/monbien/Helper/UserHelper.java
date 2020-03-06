package com.example.amine.monbien.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.amine.monbien.Contract.UserContract;


public class UserHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION =1;
    public static final String DB_NAME ="User.db";
    public static final String COMMA_SEP = ",";
    public static final String TEXT_TYPE =" TEXT";


    public static final String CREATE_USER_TABLE_SQL =
            "CREATE TABLE "+ UserContract.UserEntry.TABLE_NAME +" ("
                    + UserContract.UserEntry._ID+" INTEGER PRIMARY KEY"+COMMA_SEP
                    +UserContract.UserEntry.ID+TEXT_TYPE+COMMA_SEP
                    +UserContract.UserEntry.NOM+TEXT_TYPE+COMMA_SEP
                    +UserContract.UserEntry.EMAIL+TEXT_TYPE+COMMA_SEP
                    +UserContract.UserEntry.PASSWORD+TEXT_TYPE+COMMA_SEP
                    +UserContract.UserEntry.ROLE+TEXT_TYPE+")";

    public UserHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE_SQL);
        onCreate(sqLiteDatabase);
    }
}
