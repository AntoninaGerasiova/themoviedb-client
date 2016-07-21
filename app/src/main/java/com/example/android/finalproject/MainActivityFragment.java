package com.example.android.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.finalproject.info.MovieInfo;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    //public constants for Intent's extras
    public static final String MOVIE_INFO = "movie_info";

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

                //Intent intent = new Intent(getActivity(), DetailActivity.class);
                //intent.putExtra(MOVIE_INFO, movieInfo);
                //startActivity(intent);

                callback.onItemSelected(movieInfo);

            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        //get info about movies from internet every time when activity started
        new FetchMoviesTask(getActivity(), mAdapter).execute();
    }
}
