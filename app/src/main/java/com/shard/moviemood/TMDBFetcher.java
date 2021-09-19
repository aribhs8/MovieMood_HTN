package com.shard.moviemood;

import android.net.Uri;
import android.util.Log;

import com.shard.moviemood.models.MovieItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TMDBFetcher
{
    private static final String API_KEY = "4a64527d2f146d36c6821e790b2d77e7";
    public byte[] getUrlBytes(String urlSpec) throws IOException
    {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
            {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally
        {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException
    {
        return new String(getUrlBytes(urlSpec));
    }

    public List<MovieItem> fetchItems(String genreID, String excludeGenre, String rel_lower, String rel_upper, String keywords)
    {
        List<MovieItem> items = new ArrayList<>();
        try
        {
            String url = Uri.parse("https://api.themoviedb.org/3/discover/movie")
                    .buildUpon()
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("language", "en-US")
                    .appendQueryParameter("sort_by", "popularity.desc")
                    .appendQueryParameter("with_genres", genreID)
                    .appendQueryParameter("without_genres", excludeGenre)
                    .appendQueryParameter("release_date.gte", rel_lower)
                    .appendQueryParameter("release_date.lte", rel_upper)
                    .appendQueryParameter("with_keywords", keywords)
                    .build().toString();

            Log.i("MovieMood", url);
            String jsonString = getUrlString(url);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        } catch (IOException ioe)
        {
            Log.e("MovieMood", "Failed to fetch items", ioe);
        } catch (JSONException je)
        {
            Log.e("MovieMood", "Failed to parse JSON", je);
        }

        return items;
    }

    private void parseItems(List<MovieItem> items, JSONObject jsonBody) throws IOException, JSONException
    {
        JSONArray moviesJsonArray = jsonBody.getJSONArray("results");

        for (int i = 0; i < moviesJsonArray.length(); i++)
        {
            JSONObject movieJsonObject = moviesJsonArray.getJSONObject(i);
            MovieItem item = new MovieItem();
            item.setId(movieJsonObject.getString("id"));
            item.setTitle(movieJsonObject.getString("title"));
            item.setRating(movieJsonObject.getDouble("vote_average"));
            item.setDate(movieJsonObject.getString("release_date"));
            item.setPlot(movieJsonObject.getString("overview"));
            item.setImagePath(movieJsonObject.getString("poster_path"));

            Log.i("MovieMood", item.getTitle());

            items.add(item);
        }
    }
}
