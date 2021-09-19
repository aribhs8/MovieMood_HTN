package com.shard.moviemood.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.shard.moviemood.R;
import com.shard.moviemood.TMDBFetcher;
import com.shard.moviemood.fragments.MovieListingsFragment;
import com.shard.moviemood.models.MovieItem;

import java.util.HashMap;
import java.util.List;

public class MoviePagerActivity extends AppCompatActivity
{
    private ViewPager2 mViewPager;
    private List<MovieItem> mItems;

    public static Intent newIntent(Context packageContext, HashMap<String, String> options)
    {
        Intent intent = new Intent(packageContext, MoviePagerActivity.class);
        intent.putExtra("company", options.get("company"));
        intent.putExtra("genre", options.get("genreID"));
        intent.putExtra("date", options.get("release_date"));
        intent.putExtra("emotion", options.get("emotion"));
        intent.putExtra("animated", options.get("animated"));
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_pager);

        mViewPager = (ViewPager2) findViewById(R.id.movie_view_pager);

        String genre = getIntent().getStringExtra("genre");
        String withoutGenre = "";
        String animated = getIntent().getStringExtra("animated");
        String rel_lower = "";
        String rel_upper = "";
        String date = getIntent().getStringExtra("date");
        String keywords = getIntent().getStringExtra("company");
        String emotion = getIntent().getStringExtra("emotion");

        if (animated.equals("yes"))
        {
            if (genre.equals(""))
            {
                genre = "16";
            } else
            {
                genre += ",16";
            }
        } else
        {
            withoutGenre = "16";
        }

        if (keywords.isEmpty())
        {
            keywords = emotion;
        } else
        {
            keywords += "," + emotion;
        }

        if (date.equals("2000"))
        {
            rel_lower = "2000-01-01";
        } else if (date.equals("1980"))
        {
            rel_lower = "1980-01-01";
            rel_upper = "1999-12-31";
        } else if (date.equals("1960"))
        {
            rel_lower = "1960-01-01";
            rel_upper = "1979-12-31";
        }

        new FetchItemsTask(
                genre,
                withoutGenre,
                rel_lower,
                rel_upper,
                keywords
        ).execute();
    }

    private void updateUI()
    {
        Log.i("MovieMood", "update");
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStateAdapter(fm, getLifecycle())
        {
            @NonNull
            @Override
            public Fragment createFragment(int position)
            {
                MovieItem item = mItems.get(position);
                return MovieListingsFragment.newInstance(item);
            }

            @Override
            public int getItemCount()
            {
                return mItems.size();
            }
        });
    }

    @SuppressWarnings("deprecation")
    private class FetchItemsTask extends AsyncTask<Void, Void, List<MovieItem>>
    {
        private final String mGenre;
        private final String mExcludeGenre;
        private final String mReleaseLower;
        private final String mReleaseUpper;
        private final String mKeywords;

        FetchItemsTask(String genreID, String withoutGenre, String rel_lower, String rel_upper, String keywords)
        {
            mGenre = genreID;
            mExcludeGenre = withoutGenre;
            mReleaseLower = rel_lower;
            mReleaseUpper = rel_upper;
            mKeywords = keywords;
        }

        @Override
        protected List<MovieItem> doInBackground(Void... voids)
        {
            return new TMDBFetcher().fetchItems(mGenre, mExcludeGenre, mReleaseLower, mReleaseUpper, mKeywords);
        }

        @Override
        protected void onPostExecute(List<MovieItem> items)
        {
            mItems = items;
            updateUI();
        }
    }
}
