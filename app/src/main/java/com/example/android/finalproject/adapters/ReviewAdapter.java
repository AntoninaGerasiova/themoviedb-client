package com.example.android.finalproject.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.android.finalproject.R;
import com.example.android.finalproject.model.Review;
import com.example.android.finalproject.model.Trailer;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 14.09.16.
 */
public class ReviewAdapter extends ArrayAdapter<Review> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Review> reviews;

    public ReviewAdapter(Context context, int resource, ArrayList<Review> reviews) {
        super(context, R.layout.review_item, reviews);
        this.context = context;
        this.reviews = reviews;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (null == view) {
            view = inflater.inflate(R.layout.review_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        }
        final Review review = reviews.get(position);
        viewHolder  = (ViewHolder) view.getTag();
        viewHolder.authorView.setText(review.getAuthor());
        viewHolder.contentView.setText(Html.fromHtml(review.getContent()));
        return view;
    }


    public static class ViewHolder {
        public final TextView authorView;
        public final TextView contentView;

        public ViewHolder(View view) {
            authorView = (TextView) view.findViewById(R.id.review_author);
            contentView = (TextView) view.findViewById(R.id.review_content);
        }
    }
}
