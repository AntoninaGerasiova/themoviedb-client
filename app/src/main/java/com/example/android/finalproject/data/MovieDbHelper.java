package com.example.android.finalproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.finalproject.data.MovieContract.MovieEntry;


public class MovieDbHelper  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "movie.db";


    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieEntry.TABLE_NAME + " (" +
                MovieEntry._ID + " INTEGER PRIMARY KEY," +
                MovieEntry.MOVIE_ID + " INTEGER UNIQUE NOT NULL, " +
                MovieEntry.TITLE + " TEXT NOT NULL, " +
                MovieEntry.POSTER + " TEXT NOT NULL, " +
                MovieEntry.BACKDROP + " TEXT NOT NULL, " +
                MovieEntry.OVERVIEW + " TEXT NOT NULL, " +
                MovieEntry.VOTE + " REAL NOT NULL, " +
                MovieEntry.POPULARITY +  " REAL NOT NULL, " +
                MovieEntry.RELEASE_DATE + " TEXT NOT NULL " +
                " );";
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
