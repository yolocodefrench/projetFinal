package com.pierre.projetfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Utilisateur on 10/04/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOM_BASE = "pays.db";
    private static final int VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, NOM_BASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pays(" +
                "    id integer primary key not null," +
                "    name String," +
                "    flag String," +
                "    capital String," +
                "    continent String," +
                "    date String"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pays;" +
                " CREATE TABLE  pays(" +
                "    id integer primary key not null," +
                "    name String," +
                "    flag String," +
                "    capital String," +
                "    continent String," +
                "    date String"+
                ")");
        onCreate(db);
    }

}
