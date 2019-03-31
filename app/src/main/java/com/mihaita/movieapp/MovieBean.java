package com.mihaita.movieapp;

import java.io.Serializable;

public class MovieBean implements Serializable {

    public static final String TABLE_NAME = "movie";

    public static final String COLUMN_ID = "movie_id";
    public static final String COLUMN_MOVIENAME = "movie_name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_ACTIVE = "active";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_MOVIENAME + " TEXT," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_RATING + " FLOAT," +
                    COLUMN_ACTIVE + " INTEGER)";

    private int movieID;
    private String movieName;
    private String movieDescription;
    private boolean activeFlag;
    private float movieRating;

    public MovieBean(String movieName, String movieDescription, boolean activeFlag, float movieRating) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.activeFlag = activeFlag;
        this.movieRating = movieRating;
    }

    public MovieBean() {
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public float getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(float movieRating) {
        this.movieRating = movieRating;
    }
}
