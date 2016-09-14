package com.example.android.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.finalproject.R;
import com.example.android.finalproject.Utility;
import com.example.android.finalproject.model.Trailer;

import java.util.ArrayList;

/**
 * Created by tony on 13.09.16.
 */
public class TrailerAdapter extends ArrayAdapter<Trailer> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Trailer> trailers;
    public TrailerAdapter(Context context, ArrayList<Trailer> trailers) {
        super(context, R.layout.trailer_item, trailers);
        this.context = context;
        this.trailers = trailers;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (null == view) {
            view = inflater.inflate(R.layout.trailer_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        }
        final Trailer trailer = trailers.get(position);
        viewHolder  = (ViewHolder) view.getTag();

        String youtubeThumbnailURL = Utility.getYoutubeThumbnailURL(trailer.getKey());

        Glide.with(getContext()).load(youtubeThumbnailURL).into(viewHolder.imageView);

        viewHolder.textView.setText(trailer.getName());



        return view;
    }

    public static class ViewHolder {
        public final ImageView imageView;
        public final TextView textView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.trailer_image);
            textView = (TextView) view.findViewById(R.id.trailer_name);
        }
    }
}
