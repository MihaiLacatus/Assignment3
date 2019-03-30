package com.mihaita.movieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBClass extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 1;

    public DBClass(Context context) { super(context, MovieBean.TABLE_NAME, null, DATABASE_VERSION); }

    public MovieBean addMovie(MovieBean movie){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieBean.COLUMN_MOVIENAME, movie.getMovieName());
        values.put(MovieBean.COLUMN_DESCRIPTION, movie.getMovieDescription());
        values.put(MovieBean.COLUMN_RATING, movie.getMovieRating());
        values.put(MovieBean.COLUMN_ACTIVE, movie.isActiveFlag());

        db.insert(MovieBean.TABLE_NAME, null,values);

        return movie;
    }

    public List<MovieBean> getMovies(){

        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "select * from " + MovieBean.TABLE_NAME;

        Cursor c = db.rawQuery(selectQuery, null);

        List<MovieBean> moviesList = new ArrayList<>();

        if (c.moveToFirst())
        {
            do {
                MovieBean foundMovie = new MovieBean();
                foundMovie.setMovieID(c.getInt(c.getColumnIndex(MovieBean.COLUMN_ID)));
                foundMovie.setMovieName(c.getString(c.getColumnIndex(MovieBean.COLUMN_MOVIENAME)));
                foundMovie.setMovieDescription(c.getString(c.getColumnIndex(MovieBean.COLUMN_DESCRIPTION)));
                foundMovie.setMovieRating(c.getFloat((c.getColumnIndex(MovieBean.COLUMN_RATING))));
                foundMovie.setActiveFlag(c.getInt(c.getColumnIndex(MovieBean.COLUMN_ACTIVE)) !=0);

                moviesList.add(foundMovie);

            } while (c.moveToNext());
        }
        db.close();
        return moviesList;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MovieBean.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
