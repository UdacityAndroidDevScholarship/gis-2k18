package com.udacity.googleindiascholarships.projects.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProjectDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gis2k18.db";

    public ProjectDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_PROJECTS_TABLE = "CREATE TABLE "+ ProjectContract.ProjectEntry.TABLE_NAME+" ("
                +ProjectContract.ProjectEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ProjectContract.ProjectEntry.COLUMN_PROJECT_NAME+" TEXT NOT NULL,"
                +ProjectContract.ProjectEntry.COLUMN_PROJECT_DESCRIPTION+" TEXT NOT NULL,"
                +ProjectContract.ProjectEntry.COLUMN_PROJECT_GITHUB_URL+" TEXT NOT NULL,"
                + ProjectContract.ProjectEntry.COLUMN_PROJECT_LOGO_URL+" TEXT NOT NULL);";


        sqLiteDatabase.execSQL(SQL_CREATE_PROJECTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
