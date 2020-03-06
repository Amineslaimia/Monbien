package com.example.amine.monbien.Contract;

import android.provider.BaseColumns;

public class UserContract {
    public static class UserEntry implements BaseColumns {
        public static final String ID = "id";
        public static final String NOM = "Nom";
        public static final String EMAIL = "Email";
        public static final String PASSWORD = "Password";
        public static final String ROLE = "Role";
        public static final String TABLE_NAME = "User";

    }
}
