package com.example.android.finalproject;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    ImageAdapter mAdapter;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        //ImageView testView = (ImageView) rootView.findViewById(R.id.test_view);
        //Picasso.with(getActivity().getApplicationContext()).load("http://image.tmdb.org/t/p/w92/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg").into(testView);
        GridView posterGridView = (GridView) rootView.findViewById(R.id.posters_gridview);
        mAdapter = new ImageAdapter(getActivity().getApplicationContext(), new ArrayList<MovieInfo>());
        posterGridView.setAdapter(mAdapter);
        new FetchMoviesTask().execute();
        return rootView;
    }

    public class FetchMoviesTask extends AsyncTask<Void, Void, MovieInfo[]> {
        private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

        @Override
        protected MovieInfo[] doInBackground(Void... params) {
             // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String resultJsonStr = null;

            try {
                 // Construct the URL for  themoviedb.org query

                final String BASE_URL = "http://api.themoviedb.org/3/movie/popular?";
                final String APIKEY_PARAM = "api_key";

                Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter(APIKEY_PARAM,  BuildConfig.THEMOVIEDB_API_KEY)
                        .build();

                Log.v(LOG_TAG, "builtUri: " + builtUri);
                URL url = new URL(builtUri.toString());

                // Create the request to themoviedb.org, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                 // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }

                resultJsonStr = buffer.toString();
                Log.v(LOG_TAG, "Result JSON String: " + resultJsonStr);

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the movies data, there's no point in attempting
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }


            try {
                return getMovieInfoFromJson(resultJsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

             // This will only happen if there was an error getting or parsing the forecast.
            return null;

        }

        /**
         * Take the String representing the information about movies in JSON Format and
         * pull out the data we need to construct the urls to the movie posters
         *
         */
        private MovieInfo[] getMovieInfoFromJson (String resultJsonStr)
                throws JSONException
        {

             // These are the names of the JSON objects that need to be extracted.
            final String RESULTS = "results";
            final String POSTER_PATH = "poster_path";
            final String TITLE = "title";
            final String OVERVIEW = "overview";
            final String VOTE_AVERAGE = "vote_average";
            final String RELEASE_DATE = "release_date";

            JSONObject resultJson = new JSONObject(resultJsonStr);
            JSONArray movieArray = resultJson.getJSONArray(RESULTS);

            MovieInfo[] resultInfo = new MovieInfo[movieArray.length()];

            //populate movieArray with MovieInfo objects. Get info from JSON string
            for(int i = 0; i < movieArray.length(); i++) {
                JSONObject nextMovieJSON = movieArray.getJSONObject(i);
                resultInfo[i] = new MovieInfo();
                resultInfo[i].setPosterAddress(makeFullPath(nextMovieJSON.getString(POSTER_PATH)));
                resultInfo[i].setTitle(nextMovieJSON.getString(TITLE));
                resultInfo[i].setOverview(nextMovieJSON.getString(OVERVIEW));
                resultInfo[i].setVoteAverage(nextMovieJSON.getString(VOTE_AVERAGE));
                resultInfo[i].setReleaseDate(nextMovieJSON.getString(RELEASE_DATE));

                //Log.v(LOG_TAG, resultInfo[i].toString());
            }
            return resultInfo;
        }

        /**
         * Make full path from partPath. May be complicated later
         * @param partPath - part path to poster
         * @return full path to poster
         */
        private String makeFullPath(String partPath) {
           return  "http://image.tmdb.org/t/p/" + "w154" + partPath;
        }

        /**
         * add posters to the adapter
         * @param result
         */
        @Override
        protected void onPostExecute(MovieInfo[] result) {
            //if it is som result populate adapter with MovieInfo objects
            if (result != null) {
                mAdapter.clear();
                for (MovieInfo movie : result) {
                        mAdapter.add(movie);
                }
            }
        }
    }
}
