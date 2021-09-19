package com.shard.moviemood.activities;

import androidx.fragment.app.Fragment;

import com.shard.moviemood.SingleFragmentActivity;
import com.shard.moviemood.fragments.InformationFragment;
import com.shard.moviemood.fragments.MovieListingsFragment;

public class InformationActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment()
    {
        return InformationFragment.newInstance();
    }
}