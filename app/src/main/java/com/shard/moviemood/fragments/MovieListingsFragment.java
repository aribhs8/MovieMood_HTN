package com.shard.moviemood.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shard.moviemood.R;
import com.shard.moviemood.models.MovieItem;

import java.io.InputStream;

public class MovieListingsFragment extends Fragment {
    private final MovieItem mItem;

    private TextView mTitleTextView;
    private TextView mRatingTextView;
    private TextView mDateTextView;
    private ImageView mImageView;
    private TextView mPlotTextView;

    public static MovieListingsFragment newInstance(MovieItem item) {
        return new MovieListingsFragment(item);
    }

    private MovieListingsFragment(MovieItem item)
    {
        mItem = item;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie, container, false);

        mTitleTextView = (TextView) v.findViewById(R.id.movie_title);
        mRatingTextView = (TextView) v.findViewById(R.id.movie_rating);
        mDateTextView = (TextView) v.findViewById(R.id.movie_date);
        mImageView = (ImageView) v.findViewById(R.id.movie_poster);
        mPlotTextView = (TextView) v.findViewById(R.id.movie_plot);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitleTextView.setText(mItem.getTitle());
        mRatingTextView.setText(String.valueOf(mItem.getRating()));
        mDateTextView.setText(mItem.getDate());
        new DownloadImageTask(mImageView).execute(mItem.getImagePath());
        mPlotTextView.setText(mItem.getPlot());
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
    {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}



