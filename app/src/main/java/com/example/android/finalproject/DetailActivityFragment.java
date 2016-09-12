package com.example.android.finalproject;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.finalproject.data.MovieContract;
import com.example.android.finalproject.info.MovieInfo;
import com.example.android.finalproject.info.Trailer;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    public static final int POSTER_WIDTH = 342;

    //key for getArguments
    private static final String MOVIE_INFO = "movie_info";

    //MovieInfo object
    private MovieInfo mMovieInfo;
    //whether movie is in favorite or not
    private boolean mFavorite;

    private TextView mTitleView;
    private ImageView mPosterView;
    private TextView mOverviewView;
    private TextView mRatingView;
    private TextView mReleaseView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_detail, menu);
        MenuItem favoriteMenuItem = menu.findItem(R.id.action_favorite);
        if (mFavorite) {
            favoriteMenuItem.setIcon(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
        }
        else {
            favoriteMenuItem.setIcon(R.drawable.abc_btn_rating_star_off_mtrl_alpha);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            if (!mFavorite) {
                //add movie to database
                Log.v(LOG_TAG, mMovieInfo.toString());
                ContentValues contentValues = mMovieInfo.createContentValues();
                Log.v(LOG_TAG, contentValues.toString());
                Uri movieUri = getActivity().getContentResolver().insert(
                        MovieContract.MovieEntry.CONTENT_URI,
                        mMovieInfo.createContentValues());
                long locationRowId = ContentUris.parseId(movieUri);

                if(locationRowId != -1) {
                    mFavorite = true;
                    item.setIcon(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
                    Toast.makeText(getActivity(), getString(R.string.toast_add_movie), Toast.LENGTH_LONG).
                            show();
                }
            }
            else {
                //remove movie to database
                int rowsDeleted = getActivity().getContentResolver().delete(
                        MovieContract.MovieEntry.CONTENT_URI,
                        MovieContract.MovieEntry.MOVIE_ID + "= ?",
                        new String[]{Integer.toString(mMovieInfo.getMovieId())});
                Log.v(LOG_TAG, String.valueOf(rowsDeleted));
                if (rowsDeleted != 0) {
                    mFavorite = false;
                    item.setIcon(R.drawable.abc_btn_rating_star_off_mtrl_alpha);
                    Toast.makeText(getActivity(), getString(R.string.toast_delete_movie), Toast.LENGTH_LONG).
                            show();
                }

            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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


        //Get information from args and fill the views of the fragment_detail.xml

        Bundle arguments = getArguments();

        if (arguments != null) {
            //get MovieInfo object from intent
            mMovieInfo = arguments.getParcelable(DetailActivityFragment.MOVIE_INFO);

            //fill all the views
            mTitleView.setText(mMovieInfo.getTitle());
            String  posterAddress = Utility.makeFullPath(POSTER_WIDTH, mMovieInfo.getBackdropAddress());
            Picasso.with(getActivity().getApplicationContext()).load(posterAddress).into(mPosterView);
            mOverviewView.setText(mMovieInfo.getOverview());
            mRatingView.setText(String.valueOf(mMovieInfo.getVoteAverage()));
            mReleaseView.setText(mMovieInfo.getReleaseDate());

            //get cursor for this movie using MovieProvider
            int movieId = mMovieInfo.getMovieId();
            Cursor movieCursor = getActivity().getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI,
                    null,
                    MovieContract.MovieEntry.MOVIE_ID + "= ?",
                    new String[]{Integer.toString(movieId)},
                    null);

            //if there is the movie in the database than it was already marked is favorite
            if (movieCursor != null && movieCursor.moveToFirst() != false)
                mFavorite = true;

            movieCursor.close();


        }
        return rootView;
    }

    //AsyncTask to get trailers information
    public class FetchTrailersTask extends AsyncTask<String, Void, List<Trailer>> {

        @Override
        protected List<Trailer> doInBackground(String... params) {
            return null;
        }
    }

}
