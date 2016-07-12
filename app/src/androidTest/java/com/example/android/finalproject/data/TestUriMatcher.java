package com.example.android.finalproject.data;

import android.content.UriMatcher;
import android.net.Uri;
import android.test.AndroidTestCase;


public class TestUriMatcher extends AndroidTestCase {

    // content://com.example.android.finalproject/movie"
    private static final Uri TEST_WEATHER_DIR = MovieContract.MovieEntry.CONTENT_URI;

    public void testUriMatcher() {
        UriMatcher testMatcher = MovieProvider.buildUriMatcher();

        assertEquals("Error: The WEATHER URI was matched incorrectly.",
                testMatcher.match(TEST_WEATHER_DIR), MovieProvider.MOVIE);
    }
}
