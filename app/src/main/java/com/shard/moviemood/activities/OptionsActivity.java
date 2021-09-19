package com.shard.moviemood.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.shard.moviemood.R;
import com.shard.moviemood.SingleFragmentActivity;
import com.shard.moviemood.TMDBFetcher;
import com.shard.moviemood.fragments.PeopleFragment;
import com.shard.moviemood.interfaces.OptionCallbacks;
import com.shard.moviemood.models.MovieItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OptionsActivity extends SingleFragmentActivity implements OptionCallbacks
{
    private final HashMap<String, String> mOptions = new HashMap<String, String>();

    @Override
    protected Fragment createFragment()
    {
        return PeopleFragment.newInstance();
    }


    @Override
    public void onDataPass(String key, String data)
    {
        mOptions.put(key, data);
    }

    @Override
    public void onAllOptionsSelected()
    {
        Intent intent = MoviePagerActivity.newIntent(getApplicationContext(), mOptions);
        startActivity(intent);
    }
}