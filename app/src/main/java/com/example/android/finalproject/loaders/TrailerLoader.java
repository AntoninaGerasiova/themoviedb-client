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

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by tony on 15.09.16.
 */
public class TrailerLoader extends AsyncTaskLoader<List<Trailer>> {

    private final String LOG_TAG = TrailerLoader.class.getSimpleName();
    Context mContext;
    Integer mMovieId;
    ArrayList<Trailer> trailers;


    public TrailerLoader(Context context, Integer movieId) {
        super(context);
        mContext = context;
        mMovieId = movieId;
    }

    // as I understand  call of forceLoad() is mandatory for class which extends AsyncTaskLoader
    //or it never starts loadInBackground()
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(LOG_TAG, hashCode() + " onStartLoading");
        forceLoad();
    }

    @Override
    public List<Trailer> loadInBackground() {

        String trailerURL = Utility.getTrailersURL(mMovieId);
        //Log.d(LOG_TAG, "trailerURL: " + trailerURL);
        AsyncHttpClient client = new SyncHttpClient();

        client.get(trailerURL, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                trailers = new ArrayList<>();
                try {
                    JSONArray trailersJSONArray = response.getJSONArray("results");
                    //Log.d(LOG_TAG, "trailersJSONArray: " + trailersJSONArray);
                    trailers.clear();
                    for (int i = 0; i < trailersJSONArray.length(); i++) {

                        JSONObject trailerJSON  = trailersJSONArray
                                .getJSONObject(i);
                        Trailer trailer = new Trailer(trailerJSON);
                        //Log.d(LOG_TAG, trailer.toString());
                        trailers.add(trailer);
                    }

                } catch (JSONException e) {
                    Log.e(LOG_TAG, e.getMessage(), e);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(LOG_TAG, "Unable to fetch trailers. Status code: " + statusCode);
            }

        });
        return trailers;
    }

}
