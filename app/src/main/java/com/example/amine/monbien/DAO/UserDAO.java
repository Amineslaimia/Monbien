package com.example.amine.monbien.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.amine.monbien.Contract.UserContract;
import com.example.amine.monbien.Helper.UserHelper;
import com.example.amine.monbien.entities.User;



public class UserDAO {
    UserHelper userHelper;
    public UserDAO(Context context) { userHelper = new UserHelper(context);
    }

    public long insertUser(User user){
        SQLiteDatabase db = userHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(UserContract.UserEntry.ID,user.getId());
        cv.put(UserContract.UserEntry.NOM,user.getNom());
        cv.put(UserContract.UserEntry.EMAIL,user.getEmail());
        cv.put(UserContract.UserEntry.PASSWORD,user.getPassword());
        cv.put(UserContract.UserEntry.ROLE,user.getRole());
        return db.insert(UserContract.UserEntry.TABLE_NAME,null,cv);
    }
    public List<User> GetAllUser(){
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = userHelper.getReadableDatabase();
        Cursor cursor = db.query(UserContract.UserEntry.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            User user = new User();
            user.setId(cursor.getString(1));
            user.setNom(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setPassword(cursor.getString(4));
            user.setRole(cursor.getString(5));
            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }
    public int Delete(){
        SQLiteDatabase db = userHelper.getWritableDatabase();
        return  db.delete(UserContract.UserEntry.TABLE_NAME, "1", null);
    }
}
