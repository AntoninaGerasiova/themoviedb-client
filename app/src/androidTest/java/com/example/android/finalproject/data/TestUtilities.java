package com.example.android.finalproject.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import java.util.Map;
import java.util.Set;


public class TestUtilities extends AndroidTestCase {

    static ContentValues createMovieValues() {
        ContentValues testValues = new ContentValues();
        testValues.put(MovieContract.MovieEntry.MOVIE_ID, 368596);
        testValues.put(MovieContract.MovieEntry.TITLE, "Back in the Day");
        testValues.put(MovieContract.MovieEntry.POSTER, "/is6QqgiPQlI3Wmk0bovqUFKM56B.jpg");
        testValues.put(MovieContract.MovieEntry.BACKDROP, "/yySmUG29VgDdCROb9eer9L2kkKX.jpg");
        testValues.put(MovieContract.MovieEntry.OVERVIEW, "A young boxer is taken under the wing of a mob boss after his mother dies and his father is run out of town for being an abusive alcoholic.");
        testValues.put(MovieContract.MovieEntry.VOTE, 3.19);
        testValues.put(MovieContract.MovieEntry.POPULARITY, 71.27);
        testValues.put(MovieContract.MovieEntry.RELEASE_DATE, "2016-05-20");
        return testValues;
    }

    static long insertMovieValues(Context context) {
        // insert our test records into the database
        MovieDbHelper dbHelper = new MovieDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues testValues = TestUtilities.createMovieValues();

        long locationRowId;
        locationRowId = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, testValues);

        // Verify we got a row back.
        assertTrue("Error: Failure to insert Movie Values", locationRowId != -1);

        return locationRowId;
    }

    static void validateCursor(String error, Cursor valueCursor, ContentValues expectedValues) {
        assertTrue("Empty cursor returned. " + error, valueCursor.moveToFirst());
        validateCurrentRecord(error, valueCursor, expectedValues);
        valueCursor.close();
    }


    static void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues) {
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for (Map.Entry<String, Object> entry : valueSet) {
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse("Column '" + columnName + "' not found. " + error, idx == -1);
            String expectedValue = entry.getValue().toString();
            assertEquals("Value '" + entry.getValue().toString() +
                    "' did not match the expected value '" +
                    expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx));
        }
    }
}
