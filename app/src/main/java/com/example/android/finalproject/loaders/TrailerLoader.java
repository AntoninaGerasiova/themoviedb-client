package com.example.android.finalproject.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.android.finalproject.Utility;
import com.example.android.finalproject.model.Review;
import com.example.android.finalproject.model.Trailer;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by tony on 15.09.16.
 */
public class TrailerLoader extends BaseDetailLoader<Trailer> {

    public TrailerLoader(Context context, Integer movieId) {
        super(context, movieId);
    }

    @Override
    protected String getDetailURL() {
        return Utility.getTrailersURL(getmMovieId());
    }

    @Override
    protected void appendToList(List<Trailer> list, JSONObject resultJSON) {
            appendToList(Trailer.class,  list, resultJSON);
    }


}
