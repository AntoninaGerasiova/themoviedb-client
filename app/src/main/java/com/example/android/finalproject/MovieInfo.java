package com.example.android.finalproject;

/**
 * Keeps information about class
 */
public class MovieInfo {
    private String posterAddress;
    private String title;
    private String overview;
    private String voteAverage;
    private String releaseDate;

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

    @Override
    public String toString() {
        return String.format("Title: %s\nPoster Address: %s\nOverview: %s\nAverage Vote: %s\nRelease Date: %s\n",
                title,
                posterAddress,
                overview,
                voteAverage,
                releaseDate);
    }
}
