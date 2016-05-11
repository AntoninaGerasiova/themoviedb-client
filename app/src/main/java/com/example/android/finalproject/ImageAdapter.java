package com.example.android.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by tony on 11.05.16.
 */
public class ImageAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] imageUrls;

    public ImageAdapter(Context context, String[] imageUrls) {
        super(context, R.layout.grid_item, imageUrls);

        this.context = context;
        this.imageUrls = imageUrls;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
        }

        Picasso
            .with(context)
            .load(imageUrls[position])
            .fit()
            .into((ImageView) convertView);

        return convertView;
    }

}
