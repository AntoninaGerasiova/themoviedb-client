package com.example.android.finalproject;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.finalproject.adapters.ImageAdapter;
import com.example.android.finalproject.info.MovieInfo;
import com.example.android.finalproject.loaders.FetchMoviesLoader;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<MovieInfo[]> {

    private final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    //public constants for Intent's extras
    public static final String MOVIE_INFO = "movie_info";

    static final int FETCH_MOVIES_LOADER_ID = 0;

    private ImageAdapter mAdapter;



    public interface MovieSelectedCallback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        public void onItemSelected(MovieInfo movie);
    }

    public MainActivityFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        //ImageView testView = (ImageView) rootView.findViewById(R.id.test_view);
        //Picasso.with(getActivity().getApplicationContext()).load("http://image.tmdb.org/t/p/w92/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg").into(testView);
        GridView posterGridView = (GridView) rootView.findViewById(R.id.posters_gridview);
        mAdapter = new ImageAdapter(getActivity().getApplicationContext(), new ArrayList<MovieInfo>());
        posterGridView.setAdapter(mAdapter);


        //call DetailActivity when click on poster
        posterGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieInfo movieInfo = mAdapter.getItem(position);
                MovieSelectedCallback callback;
                try {
                    callback = (MovieSelectedCallback) getActivity();
                } catch (ClassCastException e) {
                    throw new ClassCastException(getActivity().getLocalClassName() + " must implement MovieSelectedCallback");
                }

                callback.onItemSelected(movieInfo);

            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //create the loader (FetchMovieLoader)
        getLoaderManager().initLoader(FETCH_MOVIES_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }


    //Loader Part
    @Override
    public Loader<MovieInfo[]> onCreateLoader(int id, Bundle args) {
        Loader <MovieInfo[]> loader = new FetchMoviesLoader(getActivity());
        //Log.d(LOG_TAG, "onCreateLoader: " + loader.hashCode());

        return loader;
    }

    /**
     * refill adapter with new information about movies
     * @param loader
     * @param data
     */
    @Override
    public void onLoadFinished(Loader<MovieInfo[]> loader, MovieInfo[] data) {
        //Log.d(LOG_TAG, "onLoadFinished for loader " + loader.hashCode()
        //        + ", data = " + data);
        refillImageAdapter(data);
    }

    @Override
    public void onLoaderReset(Loader<MovieInfo[]> loader) {

    }


    /**
     * refill the  ImageAdapter with new information about movies
     * @param info - array of MovieInfo objects with all information about movies
     */
    private void refillImageAdapter(MovieInfo[] info) {
        //if it is some result populate adapter with MovieInfo objects
        if (info != null) {
            mAdapter.clear();
            for (MovieInfo movie : info) {
                mAdapter.add(movie);
            }
        }
    }
}
