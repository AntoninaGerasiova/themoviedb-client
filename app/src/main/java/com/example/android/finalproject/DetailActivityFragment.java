package com.example.android.finalproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.finalproject.info.MovieInfo;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    public static final int POSTER_WIDTH = 154;

    private TextView mTitleView;
    private ImageView mPosterView;
    private TextView mOverviewView;
    private TextView mRatingView;
    private TextView mReleaseView;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        // find all views
        mTitleView = (TextView) rootView.findViewById(R.id.title);
        mPosterView = (ImageView)rootView.findViewById(R.id.poster);
        mOverviewView = (TextView) rootView.findViewById(R.id.overview);
        mRatingView = (TextView) rootView.findViewById(R.id.vote_average);
        mReleaseView =  (TextView) rootView.findViewById(R.id.release_date);


        //Get information from launching intent and fill the views of the fragment_detail.xml
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(MainActivityFragment.MOVIE_INFO)) {
            MovieInfo movieInfo = intent.getParcelableExtra(MainActivityFragment.MOVIE_INFO);
            mTitleView.setText(movieInfo.getTitle());
            String  posterAddress = Utility.makeFullPath(POSTER_WIDTH, movieInfo.getPosterAddress());
            Picasso.with(getActivity().getApplicationContext()).load(posterAddress).into(mPosterView);
            mOverviewView.setText(movieInfo.getOverview());
            mRatingView.setText(String.valueOf(movieInfo.getVoteAverage()));
            mReleaseView.setText(movieInfo.getReleaseDate());
        }
        return rootView;
    }
}
