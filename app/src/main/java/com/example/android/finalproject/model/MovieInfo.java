package com.example.android.finalproject.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.finalproject.data.MovieContract;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Keeps information about class
 */
public class MovieInfo implements Parcelable {
    final String MOVIE_ID = "id";
    final String POSTER_PATH = "poster_path";
    final String BACKDROP_PATH = "backdrop_path";
    final String TITLE = "title";
    final String OVERVIEW = "overview";
    final String VOTE_AVERAGE = "vote_average";
    final String POPULARITY = "popularity";
    final String RELEASE_DATE = "release_date";

    private int movieId;
    private String posterAddress;
    private String backdropAddress;
    private String title;
    private String overview;
    private double voteAverage;
    private double popularity;
    private String releaseDate;


    public MovieInfo(JSONObject object) throws JSONException {

        setMovieId(object.getInt(MOVIE_ID));
        setPosterAddress(object.getString(POSTER_PATH));
        setBackdropAddress(object.getString(BACKDROP_PATH));
        setTitle(object.getString(TITLE));
        setOverview(object.getString(OVERVIEW));
        setVoteAverage(object.getDouble(VOTE_AVERAGE));
        setPopularity(object.getDouble(POPULARITY));
        setReleaseDate(object.getString(RELEASE_DATE));

    }

    public MovieInfo(Cursor cursor) {
        setMovieId(cursor.getInt(cursor.getColumnIndex(MovieContract.MovieEntry.MOVIE_ID)));
        setPosterAddress(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.POSTER)));
        setBackdropAddress(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.BACKDROP)));
        setTitle(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.TITLE)));
        setOverview(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.OVERVIEW)));
        setVoteAverage(cursor.getDouble(cursor.getColumnIndex(MovieContract.MovieEntry.VOTE)));
        setPopularity(cursor.getDouble(cursor.getColumnIndex(MovieContract.MovieEntry.POPULARITY)));
        setReleaseDate(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.RELEASE_DATE)));
    }

    public int getMovieId() {
        return movieId;
    }

    public String getPosterAddress() {
        return posterAddress;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getBackdropAddress() {
        return backdropAddress;
    }


    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setPosterAddress(String posterAddress) {
        this.posterAddress = posterAddress;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setBackdropAddress(String backdropAddress) {
        this.backdropAddress = backdropAddress;
    }

    /**
     *
     * @return ContentValues object ready to insert to movie table with MovieProvider
     */
    public ContentValues createContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContract.MovieEntry.MOVIE_ID, getMovieId());
        contentValues.put(MovieContract.MovieEntry.TITLE, getTitle());
        contentValues.put(MovieContract.MovieEntry.POSTER, getPosterAddress());
        contentValues.put(MovieContract.MovieEntry.BACKDROP, getBackdropAddress());
        contentValues.put(MovieContract.MovieEntry.OVERVIEW, getOverview());
        contentValues.put(MovieContract.MovieEntry.VOTE, getVoteAverage());
        contentValues.put(MovieContract.MovieEntry.POPULARITY, getPopularity());
        contentValues.put(MovieContract.MovieEntry.RELEASE_DATE, getReleaseDate());
        return contentValues;
    }


    @Override
    public String toString() {
        return String.format("Movie ID: %d\n" +
                        "Title: %s\n" +
                        "Poster Address: %s\n" +
                        "Backdrop Address: %s\n" +
                        "Overview: %s\n" +
                        "Average Vote: %f\n" +
                        "Popularity: %f\n" +
                        "Release Date: %s\n",
                getMovieId(),
                getTitle(),
                getPosterAddress(),
                getBackdropAddress(),
                getOverview(),
                getVoteAverage(),
                getPopularity(),
                getReleaseDate());
    }


    //parcelable part
    public MovieInfo(Parcel in) {
        setMovieId(in.readInt());
        setPosterAddress(in.readString());
        setBackdropAddress(in.readString());
        setTitle(in.readString());
        setOverview(in.readString());
        setVoteAverage(in.readDouble());
        setPopularity(in.readDouble());
        setReleaseDate(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getMovieId());
        dest.writeString(getPosterAddress());
        dest.writeString(getBackdropAddress());
        dest.writeString(getTitle());
        dest.writeString(getOverview());
        dest.writeDouble(getVoteAverage());
        dest.writeDouble(getPopularity());
        dest.writeString(getReleaseDate());
    }

    public static final Parcelable.Creator<MovieInfo> CREATOR = new Parcelable.Creator<MovieInfo>() {

        @Override
        public MovieInfo createFromParcel(Parcel source) {
            return new MovieInfo(source);
        }

        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };
}
