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

    public DBClass(Context context) {
        super(context, MovieBean.TABLE_NAME, null, DATABASE_VERSION);
    }

    //add movies
    public MovieBean addMovie(MovieBean movie) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieBean.COLUMN_MOVIENAME, movie.getMovieName());
        values.put(MovieBean.COLUMN_DESCRIPTION, movie.getMovieDescription());
        values.put(MovieBean.COLUMN_RATING, movie.getMovieRating());
        values.put(MovieBean.COLUMN_ACTIVE, movie.isActiveFlag());

        db.insert(MovieBean.TABLE_NAME, null, values);

        return movie;
    }

    //get active movies
    public List<MovieBean> getMoviesActive() {

        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "select * from " + MovieBean.TABLE_NAME + " where " + MovieBean.COLUMN_ACTIVE + " = 1";

        Cursor c = db.rawQuery(selectQuery, null);

        List<MovieBean> moviesList = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                MovieBean foundMovie = new MovieBean();
                foundMovie.setMovieID(c.getInt(c.getColumnIndex(MovieBean.COLUMN_ID)));
                foundMovie.setMovieName(c.getString(c.getColumnIndex(MovieBean.COLUMN_MOVIENAME)));
                foundMovie.setMovieDescription(c.getString(c.getColumnIndex(MovieBean.COLUMN_DESCRIPTION)));
                foundMovie.setMovieRating(c.getFloat((c.getColumnIndex(MovieBean.COLUMN_RATING))));
                foundMovie.setActiveFlag(c.getInt(c.getColumnIndex(MovieBean.COLUMN_ACTIVE)) != 0);

                moviesList.add(foundMovie);

            } while (c.moveToNext());
        }
        db.close();
        return moviesList;
    }

    //get inactive movies
    public List<MovieBean> getMoviesInactive() {

        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "select * from " + MovieBean.TABLE_NAME + " where " + MovieBean.COLUMN_ACTIVE + " = 0";

        Cursor c = db.rawQuery(selectQuery, null);

        List<MovieBean> moviesList = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                MovieBean foundMovie = new MovieBean();
                foundMovie.setMovieID(c.getInt(c.getColumnIndex(MovieBean.COLUMN_ID)));
                foundMovie.setMovieName(c.getString(c.getColumnIndex(MovieBean.COLUMN_MOVIENAME)));
                foundMovie.setMovieDescription(c.getString(c.getColumnIndex(MovieBean.COLUMN_DESCRIPTION)));
                foundMovie.setMovieRating(c.getFloat((c.getColumnIndex(MovieBean.COLUMN_RATING))));
                foundMovie.setActiveFlag(c.getInt(c.getColumnIndex(MovieBean.COLUMN_ACTIVE)) != 0);

                moviesList.add(foundMovie);

            } while (c.moveToNext());
        }
        db.close();
        return moviesList;
    }

    //flag inactive movies
    public MovieBean updateFlagMovie(MovieBean movie){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieBean.COLUMN_ACTIVE, 0);

        db.update( MovieBean.TABLE_NAME,values,MovieBean.COLUMN_ID +" = ?",new String[]{Integer.toString(movie.getMovieID())});

        return movie;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MovieBean.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
