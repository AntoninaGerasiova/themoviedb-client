package com.example.android.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.finalproject.R;
import com.example.android.finalproject.Utility;
import com.example.android.finalproject.model.MovieInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 *ImageAdapter actually have all information about movies
 */
public class ImageAdapter extends ArrayAdapter <MovieInfo>{

    public static final int POSTER_WIDTH = 154;
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<MovieInfo> movies;

    public ImageAdapter(Context context, ArrayList<MovieInfo> movies) {
        super(context, R.layout.grid_item, movies);

        this.context = context;
        this.movies = movies;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
        }

        String posterAddress = Utility.makeFullPath(POSTER_WIDTH, movies.get(position).getPosterAddress());
        Picasso
            .with(context)
            .load(posterAddress)
            .fit()
            .into((ImageView) convertView);

        return convertView;
    }

}
