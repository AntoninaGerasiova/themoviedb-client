package com.example.android.finalproject.data;

import android.provider.BaseColumns;

/**
 * Defines table and column names for the movie database.
 */
public class MovieContract {
    /* Inner class that defines the table contents of the movie table */

    public static final class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "movie";

        // Column with the id of the movie as is in JSON
        public static final String MOVIE_ID = "movie_id";
        //title of the movie
        public static final String TITLE = "title";
        //partial address of the poster
        public static final String POSTER = "poster";
        //partial address of the backdrop
        public static final String BACKDROP = "backdrop";
        //overview of the movie
        public static final String OVERVIEW = "overview";
        //average vote of the movie
        public static final String VOTE = "vote";
        //popularity of the movie
        public static final String POPULARITY = "popularity";
        //release date of the movie
        public static final String RELEASE_DATE = "release_date";

    }
}
