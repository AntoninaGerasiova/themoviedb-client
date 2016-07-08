package com.example.android.finalproject;

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
}
