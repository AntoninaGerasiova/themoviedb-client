package com.example.android.finalproject.info;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

/**
 * Keeps information about class
 */
public class MovieInfo {
    final String MOVIE_ID = "id";
    final String POSTER_PATH = "poster_path";
    final String BACKDROP_PATH = "backdrop_path";
    final String TITLE = "title";
    final String OVERVIEW = "overview";
    final String VOTE_AVERAGE = "vote_average";
    final String RELEASE_DATE = "release_date";
    final String POPULARITY = "popularity";

    private Integer movieId;
    private String posterAddress;
    private String backdropAddress;
    private String title;

    private String overview;
    private String voteAverage;
    private String releaseDate;
    private String popularity;

    public MovieInfo(JSONObject object) throws JSONException {

        setMovieId(object.getInt(MOVIE_ID));
        setPosterAddress(object.getString(POSTER_PATH));
        setBackdropAddress(object.getString(BACKDROP_PATH));
        setTitle(object.getString(TITLE));
        setOverview(object.getString(OVERVIEW));
        setVoteAverage(object.getString(VOTE_AVERAGE));
        setReleaseDate(object.getString(RELEASE_DATE));
        setPopularity(object.getString(POPULARITY));

    }

    public Integer getMovieId() {
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

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getBackdropAddress() {
        return backdropAddress;
    }

    public void setMovieId(Integer movieId) {
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

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public void setBackdropAddress(String backdropAddress) {
        this.backdropAddress = backdropAddress;
    }

    @Override
    public String toString() {
        return String.format("Movie ID: %d\n" +
                        "Title: %s\n" +
                        "Poster Address: %s\n" +
                        "Backdrop Address: %s\n" +
                        "Overview: %s\n" +
                        "Average Vote: %s\n" +
                        "Popularity: %s\n" +
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

}
