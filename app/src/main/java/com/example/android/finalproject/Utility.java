package com.example.android.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;

/**
 * Created by tony on 08.07.16.
 */
public class Utility {

    /**
     * Make full path from partPath. May be complicated later
     * @param partPath - part path to poster
     * @param width - width of the poster
     * @return full path to poster
     */
    public static String makeFullPath(int width,String partPath)
    {
        return String.format("http://image.tmdb.org/t/p/w%d%s", width, partPath);
    }

    /**
     *  Construct the URL for  themoviedb.org query
     * @param context - context
     * @return base URL depending on sort preference
     */
    public static String getBaseURL(Context context) {
        //get sort preference
        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(context);
        String sortType = sharedPrefs.getString(
                context.getString(R.string.pref_order_key),
                context.getString(R.string.pref_order_vote));

        if (sortType.equals(context.getString(R.string.pref_order_vote))) {
            return  "http://api.themoviedb.org/3/movie/top_rated?";
        }
        else if (sortType.equals(context.getString(R.string.pref_order_popularity))) {
            return "http://api.themoviedb.org/3/movie/popular?";
        }
        else return null;
    }

    /**
     * construct URL to get trailers for movie with id movieId
     * @param movieId - movie id for with we construct URL
     * @return URL (as a String ) to get trailers for the movie with id movieId
     */
    public static String getTrailersURL(Integer movieId) {
        //String baseTrailersURL = "http://api.themoviedb.org/3/movie/209112/videos";
        final String baseTrailersURL = "http://api.themoviedb.org/3/movie/";
        final String APIKEY_PARAM = "api_key";
        Uri builtUri = Uri.parse(baseTrailersURL).buildUpon()
                .appendPath(movieId.toString())
                .appendPath("videos")
                .appendQueryParameter(APIKEY_PARAM,  BuildConfig.THEMOVIEDB_API_KEY)
                .build();
        return builtUri.toString();
    }

    public static boolean ifOrderByFavorite(Context context) {
                //get sort preference
        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(context);
        String sortType = sharedPrefs.getString(
                context.getString(R.string.pref_order_key),
                context.getString(R.string.pref_order_vote));
        if (sortType.equals(context.getString(R.string.pref_order_favorite))) {
            return  true;
        }
        return false;
    }
}
