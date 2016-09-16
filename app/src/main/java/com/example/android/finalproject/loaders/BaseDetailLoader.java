package com.example.android.finalproject.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.android.finalproject.Utility;
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
public abstract class BaseDetailLoader <E> extends AsyncTaskLoader<List<E>> {
    private final String LOG_TAG = BaseDetailLoader.class.getSimpleName();
    private  Context mContext;
    private Integer mMovieId;
    private ArrayList<E> resultList;


    public BaseDetailLoader(Context context,Integer movieId ) {
        super(context);
        mContext = context;
        mMovieId = movieId;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        //Log.d(LOG_TAG, hashCode() + " onStartLoading");
        forceLoad();
    }

    public Integer getmMovieId() {
        return mMovieId;
    }

    //abstract methods

    /**
     * generate URL to fetch detail information
     * @return URL
     */
    protected abstract String getDetailURL();

    /**
     * when instantiate just call  appendToList(Class<E> aClass, List<E> list, JSONObject resultJSON)
     * with real class name for Class<E> aClass
     * @param list
     * @param resultJSON
     */
    protected abstract void appendToList(List<E> list, JSONObject resultJSON);

    /**
     * create new object of class E from resultJSON-object and add to List<E> list
     * @param aClass - real name of the class E
     * @param list
     * @param resultJSON - parameter for constructor of class E
     */
    protected void appendToList(Class<E> aClass, List<E> list, JSONObject resultJSON) {
        try {
            E elem = aClass.getConstructor(JSONObject.class).newInstance(resultJSON);
            list.add(elem);
        } catch (NoSuchMethodException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        } catch (IllegalAccessException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        } catch (InvocationTargetException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        } catch (InstantiationException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }
    }


    @Override
    public List<E> loadInBackground() {

        //generate concrete URL for detailed information (instantiated in subclass)
        String detailURL = getDetailURL();
        //Log.d(LOG_TAG, "detailURL: " + detailURL);
        AsyncHttpClient client = new SyncHttpClient();

        client.get(detailURL, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                resultList = new ArrayList<>();
                try {
                    JSONArray resultJSONArray = response.getJSONArray("results");
                    //Log.d(LOG_TAG, "resultJSONArray: " + resultJSONArray);
                    for (int i = 0; i < resultJSONArray.length(); i++) {

                        JSONObject resultJSON  = resultJSONArray
                                .getJSONObject(i);
                        appendToList(resultList,resultJSON);
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
        return resultList;// may be null
    }



}
