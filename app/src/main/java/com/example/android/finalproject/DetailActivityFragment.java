package com.example.android.finalproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        if(intent != null) {
            if (intent.hasExtra(MainActivityFragment.TITLE)) {
                String title = intent.getStringExtra(MainActivityFragment.TITLE);
                 ((TextView) rootView.findViewById(R.id.title)).setText(title);
            }
            if (intent.hasExtra(MainActivityFragment.POSTER_PATH)) {
                String posterAddress = intent.getStringExtra(MainActivityFragment.POSTER_PATH);
                ImageView posterView = (ImageView)rootView.findViewById(R.id.poster);
                Picasso.with(getActivity().getApplicationContext()).load(posterAddress)
                        .into(posterView);

            }
            if (intent.hasExtra(MainActivityFragment.OVERVIEW)) {
                String overview = intent.getStringExtra(MainActivityFragment.OVERVIEW);
                 ((TextView) rootView.findViewById(R.id.overview)).setText(overview);
            }

            if (intent.hasExtra(MainActivityFragment.VOTE_AVERAGE)) {
                 ((TextView) rootView.findViewById(R.id.vote_average)).
                         setText(intent.getStringExtra(MainActivityFragment.VOTE_AVERAGE));
            }

            if (intent.hasExtra(MainActivityFragment.RELEASE_DATE)) {
                 ((TextView) rootView.findViewById(R.id.release_date)).
                         setText(intent.getStringExtra(MainActivityFragment.RELEASE_DATE));
            }


        }

        return rootView;
    }
}
