package com.example.android.finalproject.loaders;

import android.content.Context;


import com.example.android.finalproject.Utility;
import com.example.android.finalproject.model.Review;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by tony on 15.09.16.
 */
public class ReviewLoader  extends BaseDetailLoader<Review> {


    public ReviewLoader(Context context, Integer movieId) {
        super(context, movieId);
    }

    @Override
    protected String getDetailURL() {
        return Utility.getReviewsURL(getmMovieId());
    }

    @Override
    protected void appendToList(List<Review> list, JSONObject resultJSON) {
        appendToList(Review.class, list, resultJSON);
    }
}
