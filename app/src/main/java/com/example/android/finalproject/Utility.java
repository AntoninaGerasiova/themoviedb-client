package com.example.android.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
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
